import json
import random
import requests
import time


account_currencies = [
    "USD", "EUR", "TRY", "GBP", "CAD", "AUD", "CHF", "JPY", "INR", "NZD", "XAU"
]

account_types = [
    "SAVINGS", "CHECKING", "FIXED_DEPOSIT", "LOAN", "CREDIT_CARD", "INVESTMENT"
]

def generate_branch_code():

    return f"{random.randint(0, 9999):04d}"

def load_json(filename):
    with open(filename, "r", encoding="utf-8") as f:
        return json.load(f)

def create_payload_from_json(tc_data):
    obj = random.choice(tc_data).copy()
    payload = {
        "tcKimlikNumber": obj["tcKimlikNumber"],
        "accountCurrency": random.choice(account_currencies),
        "accountType": random.choice(account_types),
        "branchCode": generate_branch_code(),
    }
    return payload

def send_post(url, payload):
    headers = {"Content-Type": "application/json"}
    response = requests.post(url, json=payload, headers=headers)
    return response

if __name__ == "__main__":
    random.seed(time.time())

    url = "http://localhost:8090/api/individualCustomerAccount"
    tc_data = load_json("customerEntity.json")

    while True:
        payload = create_payload_from_json(tc_data)
        print("Gönderilen JSON:")
        print(payload)

        response = send_post(url, payload)
        print("Status Code:", response.status_code)
        print("Response Body:", response.text)

        #time.sleep(1)
