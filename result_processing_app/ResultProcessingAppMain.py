from confluent_kafka import Consumer, KafkaException
from ProcessingFuzzyResultService import ProcessingFuzzyResultService
import threading
import json

def handle_message(message):
    # Parse JSON and turn into dictionary 
    data = json.loads(message.value())

    user_id = data['userId']

    answers = {
        answer['questionId']: int(answer['value'])
        for answer in data['rawAnswers']['answers']
    }

    normalized_9 = ProcessingFuzzyResultService.group_and_normalize(answers)
    print("Result (9):", normalized_9)
    
    # Process values and send
    result = ProcessingFuzzyResultService.process_and_send(user_id, normalized_9)
    print("Final result:", result)

def consume_messages():
    conf = {
        'security.protocol': 'PLAINTEXT',
        'bootstrap.servers': 'localhost:9092',
        'group.id': 'async-group',
        'auto.offset.reset': 'earliest'
    }
    
    consumer = Consumer(conf)
    consumer.subscribe(['kraftt'])

    try:
        while True:
            msg = consumer.poll(0.1)
            if msg is None:
                continue
            if msg.error():
                print(f"Error: {msg.error()}")
                continue
            
            print(f"Received: {msg.value().decode('utf-8')} \n")
            handle_message(msg)

    finally:
        consumer.close()

consumer_thread = threading.Thread(target=consume_messages)
consumer_thread.daemon = True
consumer_thread.start()

print("Working detached...")
consumer_thread.join()