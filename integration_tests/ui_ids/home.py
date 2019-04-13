from lxml import html

xpath_title = r"//ion-title/div"
xpath_txf_location = r"//ion-item/div/div/ion-label[contains(text(),'Your Location')]/../ion-input/input"
xpath_txf_destination = r"//ion-item/div/div/ion-label[contains(text(),'Destination')]/../ion-input/input"
xpath_btn_get_directions = r"//button/span[contains(text(),'Get Directions')]/.."
xpath_btn_add_block = r"//button/span[contains(text(),'Add Block')]/.."
xpath_btn_simulate_blocks = r"//button/span[contains(text(),'Simulate Blocks')]/.."
xpath_btn_login = r"//button/span[contains(text(),'Login')]/.."
xpath_btn_signup = r"//button/span[contains(text(),'SignUp')]/.."

xpath_markers = r"//img[@class='leaflet-marker-icon leaflet-zoom-animated leaflet-interactive']"

def is_page(page_source):
    tree = html.fromstring(page_source)
    homepage_title = tree.xpath(xpath_title)
    if homepage_title:
        return True
    return False