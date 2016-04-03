#coding:utf-8
from lxml import etree
from multiprocessing.dummy import Pool as ThreadPool
import requests

import sys

reload(sys)

sys.setdefaultencoding('utf-8')


# title,detail,detail_url,author,time,img1,img2,img3
def towrite(contentdict):
    f.writelines(str(contentdict['title'])
                 + "," + str(contentdict['detail'])
                 + "," + str(contentdict['detail_url'])
                 + "," + str(contentdict['author'])
                 + "," + str(contentdict['time']) + ' 00:00:00'
                 + "," + str(contentdict['img1'])
                 + "," + str(contentdict['img2'])
                 + "," + str(contentdict['img3'])
                 + "\n")

def spider(url):
    html = requests.get(url)
    selector = etree.HTML(html.text)


    content_field = selector.xpath('//*[@id="newsList"]/li')


    for each in content_field:
        title = each.xpath('a/span[2]/text()')
        link = each.xpath('a/@href[last()]')
        detail_url = "http://cfm.qq.com"+link[0]


        detail = requests.get(detail_url)
        detail_text = etree.HTML(detail.text)

        # 简介
        detail_field = detail_text.xpath('//div[@id="container"]/div[@class="wrap"]/div[@class="context"]//text()')

        # //*[@id="container"]/div/div[2]/p[3]/img
        detail_img_field = detail_text.xpath('//div[@id="container"]/div[@class="wrap"]/div[@class="context"]//@src')


        print detail_url

        # 作者和时间信息
        author = detail_text.xpath('//div[@id="container"]/div[@class="wrap"]/div[@class="hd"]/p[@class="info"]/text()')

        # 作者信息
        out_author = str(author[0].encode('latin-1', 'ignore').decode('gbk','ignore'))
        # 时间信息
        out_time = str(author[1])

        print out_author
        print out_time


        # 文章详情内容
        out_detail = ""
        for field in detail_field:
            field = field.encode('latin-1', 'ignore').decode('gbk','ignore')
            print field
            out_detail = str(out_detail) + str(field) + "<br />"

        for field in detail_img_field:
            print field


        title2 = title[0].encode('latin-1').decode('gbk')
        print title2


        img1 = ""
        img2 = ""
        img3 = ""

        if len(detail_img_field) > 0:
            img1 = detail_img_field[0]

            if len(detail_img_field) > 1:
                img2 = detail_img_field[1]

                if len(detail_img_field) > 2:
                    img3 = detail_img_field[2]

        item = {}
        item['title'] = title2
        item['detail'] = out_detail.replace("\n","")
        item['detail_url'] = detail_url
        item['author'] = out_author
        item['time'] = out_time
        item['img1'] = img1
        item['img2'] = img2
        item['img3'] = img3

        towrite(item)


if __name__ == '__main__':
    pool = ThreadPool(4)
    f = open('content.csv','a')
    f.writelines("title,detail,detail_url,author,time,img1,img2,img3\n")
    page = []
    # 16年的文章
    for i in range(1,5):
        newpage = 'http://cfm.qq.com/webplat/info/news_version3/17544/17545/17597/m12810/list_'+ str(i) +'.shtml'
        page.append(newpage)

    # 15年的文章
    for i in range(1,9):
        newpage = 'http://cfm.qq.com/webplat/info/news_version3/17544/17545/17597/m12810/list_n'+ str(i) +'.shtml'

        page.append(newpage)

    results = pool.map(spider, page)
    pool.close()
    pool.join()
    f.close()





