'''
Created on Apr 7, 2019

@author: Saad
'''
import time
from integration_tests.ui_ids import home as home_ids
from integration_tests.ui_ids import login as login_ids
from integration_tests.helpers import common as common_helpers
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

def login_user(username,password,driver):
    driver.get(common_helpers.get_application_url())
    btn_to_login_page = driver.find_element_by_xpath(home_ids.xpath_btn_login)
    btn_to_login_page.click()
    WebDriverWait(driver, common_helpers.TIMEOUT).until(EC.presence_of_element_located((By.XPATH,
                                                        login_ids.xpath_btn_login)))
    
    txf_username = driver.find_element_by_xpath(login_ids.xpath_txf_username)
    txf_password = driver.find_element_by_xpath(login_ids.xpath_txf_password)
    
    common_helpers.clear_txf(txf_username)
    txf_username.send_keys(username)

    common_helpers.clear_txf(txf_password)
    txf_password.send_keys(password)

    btn_login = driver.find_element_by_xpath(login_ids.xpath_btn_login)
    btn_login.click()
    time.sleep(5)
    