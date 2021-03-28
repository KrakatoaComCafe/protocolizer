# Protocolizer
## What it is?
Protocolizer ia an ISO8583 message interpreter, or it will be when it is finish.
Current, it only accepts fixed length messages.

## Added a Protocol
Initially, a protocol needs to be added, using POST, in the URL bellow:
- https://protocolizer.herokuapp.com/protocol

An example of a JSON to be sent is showed bellow:
```sh
{
    "name": "ISO-Fake",
    "version": "21.1",
    "encoding": "ASCII",
    "fields": {
        "field002": {
            "name": "Field002",
            "length": 5
        },
        "field003": {
            "name": "Field003",
            "length": 2
        },
        "field004": {
            "name": "Field004",
            "length": 3
        }
        ,
        "field005": {
            "name": "Field005",
            "length": 4
        }
    }
}
```

## Interpret Message
After the protocol is posted, we can interpret a message.
The message needs to be sent as POST in the URL bellow:
- https://protocolizer.herokuapp.com//message/interpret

The following JSON can be sent to interpret the protocol defined above:
```sh
{
    "protocol": "ISO-Fake",
    "version": "21.1",
    "encoding": "ASCII",
    "bitmap": "5000000000000000",
    "rawData": "123451234"
}
```

## Swagger:
Any other entrypoint can be check here:
- https://protocolizer.herokuapp.com/swagger-ui.html
