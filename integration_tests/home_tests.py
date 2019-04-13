'''
Created on Apr 7, 2019

@author: Saad
'''
import time
import unittest
from lxml import html
from integration_tests.helpers import common as common_helpers
from integration_tests.helpers import signup as signup_helpers
from integration_tests.helpers import login as login_helpers
from selenium.webdriver.common.by import By
from integration_tests.ui_ids import home as home_ids
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class HomePageTests(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        cls.APPLICATION_URL = common_helpers.get_application_url()
        common_helpers.add_webdrivers_dir_to_path()

    def setUp(self):
        # Open Home page in driver
        self.driver = common_helpers.get_driver(headless=False)
        self.driver.get(self.APPLICATION_URL)

    def testHomePageUI(self):
        homepage_source = self.driver.page_source.encode('utf-8')
        
        assert home_ids.is_page(homepage_source)
        
        tree = html.fromstring(homepage_source)
        
        btn_directions = tree.xpath(home_ids.xpath_btn_get_directions)
        assert btn_directions

        btn_add_block = tree.xpath(home_ids.xpath_btn_add_block)
        assert btn_add_block
        
        btn_login = tree.xpath(home_ids.xpath_btn_login)
        assert btn_login
        
        btn_signup = tree.xpath(home_ids.xpath_btn_signup)
        assert btn_signup
        
        txf_location = tree.xpath(home_ids.xpath_txf_location)
        assert txf_location
        
        txf_destination = tree.xpath(home_ids.xpath_txf_destination)
        assert txf_destination

    def testGetDirections(self):
        username = password = str(int(time.time()))
        email = username+'@'+'gmail.com'
        signup_helpers.signup_new_user(username, password,email, 1,2,3,4,5,self.driver)

        login_helpers.login_user(username, password, self.driver)

        self.driver.get(common_helpers.get_application_url())
        
        txf_location = self.driver.find_element_by_xpath(home_ids.xpath_txf_location)
        txf_destination = self.driver.find_element_by_xpath(home_ids.xpath_txf_destination)
        
        txf_location.send_keys('Dublin')
        txf_destination.send_keys('Cork')
        
        btn_get_directions = self.driver.find_element_by_xpath(home_ids.xpath_btn_get_directions)
        btn_get_directions.click()
        time.sleep(15)
        
        markers = self.driver.find_elements_by_xpath(home_ids.xpath_markers)
        assert markers and len(markers)>2
        

    def testTransitionToSignupPage(self):
        from integration_tests.ui_ids import signup
        elem = self.driver.find_element_by_xpath(home_ids.xpath_btn_signup)
        elem.click()
        WebDriverWait(self.driver, common_helpers.TIMEOUT).until(EC.presence_of_element_located((By.XPATH,
                                                            signup.xpath_txf_password_reenter)))
        
        assert signup.is_page(self.driver.page_source.encode('utf-8'))

    def testTransitionToLoginPage(self):
        from integration_tests.ui_ids import login
        elem = self.driver.find_element_by_xpath(home_ids.xpath_btn_login)
        elem.click()
        WebDriverWait(self.driver, common_helpers.TIMEOUT).until(EC.presence_of_element_located((By.XPATH,
                                                            login.xpath_btn_login)))
        
        assert login.is_page(self.driver.page_source.encode('utf-8'))

    def tearDown(self):
        self.driver.quit()

    @classmethod
    def tearDownClass(cls):
        # Clean database
        pass

if __name__=='__main__':
    unittest.main()