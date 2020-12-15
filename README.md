# Beacon-Shopping-List

## Authors
- Pushdeep Gangrade
- Katy Mitchell
- Valerie Ray
- Rockford Stoller

## Contents
- [Video Demos](#demo)
- [API Design & Implementation](#api)
- [Authentication](#auth)

## Video Demo <a name="demo"></a>
https://youtu.be/NrmUqIN4C3k

## API Design & Implementation <a name="api"></a>

- The API used can be found at `http://104.248.113.55:3131/api/v1/store`. When the user's location is
detected to be in a specific region, the API `http://104.248.113.55:3131/api/v1/store?region=<name>` is accessed.
For example, if the bluetooth sensors indicate that the user is in the produce section,
`http://104.248.113.55:3131/api/v1/store?region=produce` is called. The API returns the items of file
discount.json that are assigned to that region:
```
[{"_id":"5fd1385cbaa0aa8d484ce8ff","discount":10,"name":"Pineapple","photo":"https://firebasestorage.googleapis.com/v0/b/chatroom-7b49e.appspot.com/o/Beacons%20Store%20Item%20Images%2Fpineapple.png?alt=media&token=aaa973b4-3b2b-410b-85b6-f7597282f596","price":1.18,"region":"produce"},
{"_id":"5fd138afbaa0aa8d484d01fa","discount":10,"name":"Oranges","photo":"https://firebasestorage.googleapis.com/v0/b/chatroom-7b49e.appspot.com/o/Beacons%20Store%20Item%20Images%2Foranges.png?alt=media&token=084e8481-e6a4-416a-b502-b9e1954555f4","price":0.89,"region":"produce"},
...]
```
- The application consists of a single window that enables the user to view the discounted items
around him while shopping in the store.
- The list of discounted items shows the item name, item image, discount, price and region.
- The regions include, "grocery", "lifesytle" and "produce". You are provided with BLE beacons which you should assign each beacon to represent a region. Please note that the provided discount information provides a region attribute, which describes the region that a specific product is located.
- When the user is in the store, the app should locate the closest beacon and present only the products for the region belonging to the closest beacon.
- As the user moves the app should contact the api to get the list of discounted items in the closest region, and the list should be refreshed to show the retrieved list of discounted products for the closest region.
- Your application should avoid oscillating between regions, which is when the app during a scan assumes region 1 then in the next scan assumes region 2, and then region 1. This case might happen when the user is equidistant from multiple beacons or due to errors in the distance estimations. This will affect the user experience and your app should present a usable solution to this problem.
- If the app is unable to locate any beacons it should display all the discounted products sorted by region.

### Beacons
<img src="https://github.com/pushpdeep-gangrade/Beacon-Shopping-List/blob/main/Sensors.jpg?raw=true" width="200" />
The Bluetooth sensors used in the app.

### Grocery Section
<img src="https://github.com/pushpdeep-gangrade/Beacon-Shopping-List/blob/main/GrocerySection.jpg?raw=true" width="200" />
The bluetooth sensors have detected the user is in the grocery section and the app displays discounted
grocery items only.

### All Sections
<img src="https://github.com/pushpdeep-gangrade/Beacon-Shopping-List/blob/main/NoBluetooth.jpg?raw=true" width="200" />
The user has denied bluetooth permissions so the app shows discounted items from all sections of the store.