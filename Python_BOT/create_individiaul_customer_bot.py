import json
import random
import requests
import time

def generate_password():
    return f"{random.randint(100000, 999999)}"

def generate_turkish_phone_number():
    number = ''.join(str(random.randint(0,9)) for _ in range(9))
    return "+905" + number

def load_json(filename):
    with open(filename, "r", encoding="utf-8") as f:
        return json.load(f)

def create_payload_from_json(data):
    obj = random.choice(data).copy()
    payload = {
        "tcKimlikNumber": obj["tcKimlikNumber"],
        "birthDate": obj["birthDate"],
        "customerEntityPassword": generate_password(),
        "customerEntityPhoneNumber": generate_turkish_phone_number()
    }
    return payload

def send_post(url, payload):
    headers = {"Content-Type": "application/json"}
    response = requests.post(url, json=payload, headers=headers)
    return response

if __name__ == "__main__":
    random.seed(time.time())

    url = "http://localhost:8090/api/individualCustomer/createIndividualCustomer"
    data = load_json("persons.json")

    while True:
        payload = create_payload_from_json(data)
        print("Gönderilen JSON:")
        print(payload)

        response = send_post(url, payload)
        print("Status Code:", response.status_code)
        print("Response Body:", response.text)

        time.sleep(0.1)
