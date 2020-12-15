// JavaScript source code

const express = require('express');
const http = require('http');
const port = process.env.PORT || 5000;
const app = express();

const cors = require('cors');
const MongoClient = require('mongodb').MongoClient;

const url = "mongodb+srv://Pushp:ETBFJKPav5POQBGA@a-mad-cluster.1u5jl.mongodb.net/Beacons?retryWrites=true&w=majority";

app.use(cors());

//Status encoded
const OK = 200;
const BAD_REQUEST = 400;
const UNAUTHORIZED = 401;
const CONFLICT = 403;
const NOT_FOUND = 404;
const INTERNAL_SERVER_ERROR = 500;

app.get('/api/v1/store', function (req, res) {

    if (req.query.region) {
        var searchObj = {
            region: req.query.region
        }
    }

    client = new MongoClient(url, {
        useNewUrlParser: true,
        useUnifiedTopology: true
    });
    client.connect().then(() => {
        client.db('Beacons').collection('Store').find( searchObj ).toArray(function (err, result) {
            if (err) {
                console.log(err);
            }
            else {
                var output = { results: result };
                res.status(OK).send(output);
            }
            return client.close();
        });
    });
});

http.createServer(app).listen(port, function () {
    console.log("Listening on port " + port);
})