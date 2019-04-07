'''
Created on Apr 7, 2019

@author: Saad
'''
import os
import webdrivers
from selenium import webdriver
from selenium.webdriver.chrome.options import Options    
from selenium.webdriver.common.keys import Keys

TIMEOUT = 60

def get_url_from_ip_port(ip,port):
    return 'http://'+ip+':'+port

def append_to_path(path):
    old_path = os.getenv('PATH')
    new_path = path+os.pathsep+old_path
    os.putenv('PATH', new_path)
    return

def add_webdrivers_dir_to_path():
    webdrivers_path = os.path.dirname(webdrivers.__file__)
    append_to_path(webdrivers_path)
    return

def get_driver(headless=False):
    options = Options()
    options.headless = headless
    driver = webdriver.Chrome(options=options)
    return driver

def clear_txf(webElement):
    webElement.send_keys(Keys.CONTROL + "a")
    webElement.send_keys(Keys.DELETE)

def get_application_url():
    UI_IP  = os.getenv('UI_IP','localhost')
    UI_PORT  = os.getenv('UI_PORT','8100')
    return get_url_from_ip_port(UI_IP,UI_PORT)