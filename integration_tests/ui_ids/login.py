'''
Created on Apr 7, 2019

@author: Saad
'''
from lxml import html

xpath_txf_username = r"//ion-item/div/div/ion-label[contains(text(),'Username')]/../ion-input/input"
xpath_txf_password = r"//ion-item/div/div/ion-label[contains(text(),'Password')]/../ion-input/input"
xpath_btn_login = r"//button/span[contains(text(),'login')]/.."

def is_page(page_source):
    tree = html.fromstring(page_source)
    elem = tree.xpath(xpath_btn_login)
    if elem:
        return True
    return False