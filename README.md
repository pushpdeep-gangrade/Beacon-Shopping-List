# Beacon-Shopping-List

## Authors
- Pushdeep Gangrade
- Katy Mitchell
- Valerie Ray
- Rockford Stoller

## Contents
- [Video Demo](#demo)
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
- The regions include, "grocery", "lifesytle" and "produce". 
- When the user is in the store, the app locates the closest beacon and presents only the products
  for that region.
- As the user moves the app should contact the API to get the list of discounted items in the closest 
  region, and the list should be refreshed to show the retrieved list of discounted products for that region.
- The app avoids oscillating between regions, such as when a user is close to multiple sensors for different regions. 
- If the app is unable to locate any beacons, such as when a user denies bluetooth permission, it displays 
  all the discounted products sorted by region.

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
