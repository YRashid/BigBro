# [Создано на хакатоне Junction 2017 Finland](https://devpost.com/software/bigbro-p7msk4)

## Inspiration
We are a group of people from Russia, where strange things can happen to your car in a parking lot. You wouldn’t believe. And we have researched this issue and based on the INTERPOL Stolen Motor Vehicle database in 2016 solely 7,257,321 motor vehicles were reported to be stolen and even more vehicles were vandalized. We believe that car theft and vandalism are international problems and we want to make a contribution to solve them. Such technology can also be applied to other types of vehicles, e.g. bicycles or motorcycles.

## What it does
Let's suppose you want to park your car. BigBro helps you do it efficiently and safely in 2 easy steps.

#### 1. SELECT PARKING LOT

You launch the application and access a map which displays parking lots and a number of free and total parking places for each of them. [comp. 1] All the parking lots are observed by cameras.

#### 2. SECURE YOUR CAR

After parking the car, the user will see live footage from the camera. All the parked cars will be auto-recognized and highlighted. [comp. 2] The user will tap his car and BigBro will start watching it. ‘Check Your Car’ notification will be sent to the owner when the BigBro detects some changes in the observed area, e.g. someone has been standing near the car for a long time or interacting with it. Moreover, the user will receive a link to GIF-file with the camera footage exposing unusual behavior in the observed area. The user will be able to decide what to do next based on this information.
Besides, we blur license plates and people on the camera footage in accordance with data protection laws in Finland.

We have also added some additional features to improve user experience.

#### MOVE YOUR CAR NOTIFICATIONS

For example, you've parked in a street where road construction is planned for today so the street needs to be empty. You will receive a notification asking you to move your car. Both car owner and the state government will benefit from it as finding the evacuated car can be tricky and tiresome for the owner and this process is expensive for the government. We do know that such program with SMS notification already exists in Finland, but it’s targeted at 5 major cities and has roughly 7,000 subscribed users. Our app will reach more users as there are some crucial benefits from using the app, e.g. efficient and safe parking.

#### SMART ADVERTISEMENT

BigBro will recognize the brand of the car and other available info and show only relevant advertisements. E.g. if the car has been left on the parking lot for several days we advertise Car Sharing and Repair Centre; if the car is dirty we advise a Car Wash Center. Moreover, owners of cheap and elite cars will see different advertisements.

## How we built it
Our product can be separated into several parts. The user interacts with an Android app built with Kotlin. Backend is written in Java and Python including Spring and Python libraries which were updated to make data processing quicker and more efficient. Particularly, we have used TensorFlow, Keras, and OpenCV. For car recognition, we have used Yolo technology, which is basically finding key points of the object and detecting its movements.

## Challenges we ran into
The most challenging thing was related to camera footage samples. This kind of data is open in Russia but turned out to be private in Finland. We couldn't find usable footage of a parking lot here in Finland. There are some cameras, which meet our criteria but it's impossible to access actual camera footage as a result of data protection laws. We decided to use footage from Russian parking lots for the proof-of-concept version. Developed software was tested and can be applied to footage from any other cameras in future.
However, we believe this kind of data should be open to the public and can be of use to society. We hope that this project will lead to further discussion and will result in making such kind of data open to the public.

## Accomplishments that we’re proud of
We were able to achieve reliable and fast cars recognition on the live video footage. Apart from that alarm system was tested for the case of stealing and proven to be effective. Additionally, we’ve made the process of free parking places search easy to interact with.

## What's next for BigBro
Improve the car recognition feature to detect car brand. That will allow us to show our users the most relevant advertisements possible.
Use bigger amount of open datasets to improve quality of user experience.
Built With: 
[Mobile app](https://github.com/mgamzatov/bigbro): kotlin
[Backend](https://github.com/YRashid/BigBro): java
[Data Analysis](https://github.com/alex27594/bigbro): python, tensorflow, keras, opencv, yolo, open-data

[More information](https://devpost.com/software/bigbro-p7msk4)


