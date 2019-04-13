'''
Created on Apr 7, 2019

@author: Saad
'''
from lxml import html

xpath_txf_username = r"//ion-item/div/div/ion-label[contains(text(),'Username')]/../ion-input/input"
xpath_txf_password = r"//ion-item/div/div/ion-label[contains(text(),'Password')]/../ion-input/input"
xpath_txf_password_reenter = r"//ion-item/div/div/ion-label[contains(text(),'Password Re-Enter')]/../ion-input/input"
xpath_txf_email = r"//ion-item/div/div/ion-label[contains(text(),'Email')]/../ion-input/input"

xpath_txf_cost = r"//ion-item/div/div/ion-label[contains(text(),'Cost')]/../ion-input/input"
xpath_txf_emission = r"//ion-item/div/div/ion-label[contains(text(),'Emission')]/../ion-input/input"
xpath_txf_health = r"//ion-item/div/div/ion-label[contains(text(),'Health')]/../ion-input/input"
xpath_txf_pollution_avoidance = r"//ion-item/div/div/ion-label[contains(text(),'Avoid Pollution')]/../ion-input/input"
xpath_txf_speed = r"//ion-item/div/div/ion-label[contains(text(),'Speed')]/../ion-input/input"
xpath_btn_signup = r"//button/span[contains(text(),'Sign Up')]"
xpath_button_back = r"//button[contains(@class,'back-button bar-button bar-button-md back-button-md bar-button-default bar-button-default-md show-back-button')]"

def is_page(page_source):
    tree = html.fromstring(page_source)
    elem = tree.xpath(xpath_txf_password_reenter)
    if elem:
        return True
    return False