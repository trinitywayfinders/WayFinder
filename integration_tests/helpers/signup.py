'''
Created on Apr 7, 2019

@author: Saad
'''
import time
from integration_tests.ui_ids import home
from integration_tests.ui_ids import signup as signup_ids
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from integration_tests.helpers import common as common_helpers
from selenium.webdriver.support import expected_conditions as EC

def signup_new_user(username,
                    password,
                    email,
                    cost,
                    emission,
                    health,
                    avoid_pollution,
                    speed,
                    driver=None):
    teardown = False
    if driver is None:
        teardown = True
        driver = common_helpers.get_driver(headless=True)
    application_url = common_helpers.get_application_url()
    driver.get(application_url)
    btn_to_signup_page = driver.find_element_by_xpath(home.xpath_btn_signup)
    btn_to_signup_page.click()
    WebDriverWait(driver, common_helpers.TIMEOUT).until(EC.presence_of_element_located((By.XPATH,
                                                        signup_ids.xpath_txf_password_reenter)))
    txf_username = driver.find_element_by_xpath(signup_ids.xpath_txf_username)
    txf_username.clear()
    txf_username.send_keys(username)
    
    txf_password = driver.find_element_by_xpath(signup_ids.xpath_txf_password)
    txf_password.clear()
    txf_password.send_keys(password)

    txf_password_reenter = driver.find_element_by_xpath(signup_ids.xpath_txf_password_reenter)
    txf_password_reenter.clear()
    txf_password_reenter.send_keys(password)
    
    txf_email = driver.find_element_by_xpath(signup_ids.xpath_txf_email)
    txf_email.clear()
    txf_email.send_keys(email)
    
    txf_cost = driver.find_element_by_xpath(signup_ids.xpath_txf_cost)
    common_helpers.clear_txf(txf_cost)
    txf_cost.send_keys(cost)
    
    txf_emission = driver.find_element_by_xpath(signup_ids.xpath_txf_emission)
    common_helpers.clear_txf(txf_emission)
    txf_emission.send_keys(emission)
    
    txf_health = driver.find_element_by_xpath(signup_ids.xpath_txf_health)
    common_helpers.clear_txf(txf_health)
    txf_health.send_keys(health)
    
    txf_speed = driver.find_element_by_xpath(signup_ids.xpath_txf_speed)
    common_helpers.clear_txf(txf_speed)
    txf_speed.send_keys(speed)
    
    txf_avoid_pollution = driver.find_element_by_xpath(signup_ids.xpath_txf_pollution_avoidance)
    common_helpers.clear_txf(txf_avoid_pollution)
    txf_avoid_pollution.send_keys(avoid_pollution)
    
    time.sleep(5)
    
    btn_signup = driver.find_element_by_xpath(signup_ids.xpath_btn_signup)
    btn_signup.click()
    
    time.sleep(5)
    
    if teardown:
        driver.quit()