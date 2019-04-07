'''
Created on Apr 7, 2019

@author: Saad
'''

import time
import unittest
from lxml import html
from selenium.webdriver.common.by import By
from integration_tests.ui_ids import home as home_ids
from integration_tests.helpers import common as common_helpers
from integration_tests.helpers import signup as signup_helpers
from selenium.webdriver.support.ui import WebDriverWait
from integration_tests.ui_ids import signup as signup_ids
from selenium.webdriver.support import expected_conditions as EC

class SignupPageTests(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        cls.APPLICATION_URL = common_helpers.get_application_url()
        common_helpers.add_webdrivers_dir_to_path()

    def setUp(self):
        # Open Signup page in driver
        self.driver = common_helpers.get_driver(headless=False)
        self.driver.get(self.APPLICATION_URL)
        elem = self.driver.find_element_by_xpath(home_ids.xpath_btn_signup)
        elem.click()
        WebDriverWait(self.driver, common_helpers.TIMEOUT).until(EC.presence_of_element_located((By.XPATH,
                                                            signup_ids.xpath_txf_password_reenter)))

    def testSignupPageUI(self):
        signup_page_source = self.driver.page_source.encode('utf-8')
        assert signup_ids.is_page(signup_page_source)
        
        tree = html.fromstring(signup_page_source)
        
        txf_username = tree.xpath(signup_ids.xpath_txf_username)
        assert txf_username
        
        txf_password = tree.xpath(signup_ids.xpath_txf_password)
        assert txf_password
        
        txf_password_reenter = tree.xpath(signup_ids.xpath_txf_password_reenter)
        assert txf_password_reenter
        
        txf_email = tree.xpath(signup_ids.xpath_txf_email)
        assert txf_email
        
        txf_cost = tree.xpath(signup_ids.xpath_txf_cost)
        assert txf_cost
        
        txf_emission = tree.xpath(signup_ids.xpath_txf_emission)
        assert txf_emission
        
        txf_health = tree.xpath(signup_ids.xpath_txf_health)
        assert txf_health
        
        txf_speed = tree.xpath(signup_ids.xpath_txf_speed)
        assert txf_speed
        
        txf_avoid_pollution = tree.xpath(signup_ids.xpath_txf_pollution_avoidance)
        assert txf_avoid_pollution
        
        btn_signup = tree.xpath(signup_ids.xpath_btn_signup)
        assert btn_signup
        

    def testTransitionToHomePage(self):
        elem = self.driver.find_element_by_xpath(signup_ids.xpath_button_back)
        elem.click()
        WebDriverWait(self.driver, common_helpers.TIMEOUT).until(EC.presence_of_element_located((By.XPATH,
                                                            home_ids.xpath_btn_get_directions)))
        assert home_ids.is_page(self.driver.page_source.encode('utf-8'))

    def tearDown(self):
        self.driver.quit()

    @classmethod
    def tearDownClass(cls):
        # Clean database
        pass

if __name__=='__main__':
    unittest.main()