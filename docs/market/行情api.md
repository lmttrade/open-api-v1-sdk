# rest api

请求错误枚举

| HTTP Response Status Code | Reason | Response Model |
| ----             | ----   | ----|
| 400              | Bad Request | {"code":"400",message":"parameter error"} |
| 404              | Not Found | {"code":"401","message":"parameter error"} |

code表明业务错误，枚举code如下

| Code |  Description |
| --- | ---|
| 400 | 参数错误 |
| 1xxxxx | xxxxxx |

## 1. 请求历史分钟线数据
### URI:/api/v1/market/candle
### HTTP-METHOD:GET
### a.请求参数

|   Name   |  Type  |            Description             | Notes |
| -------- | ------ | ---------------------------------- | ----- |
| exchange | string | HUOBI                              | [required] |
| contract | string | BTC/USDT                           | [required] |
| begin    | string | 请求开始时间(时间戳)                 | [optional] |
| end      | string | 请求结束时间(时间戳)                 | [optional] |
| size     | int    | 请求根数                            | [optional] |
| duration | string | 请求周期类型(1m.5m.15m.30m.1h.4h.1d) | [required] |

**注意：**
    如果begin 和end都填写了，则size忽略，如果begin和size填写了，end没有填写，则表示从begin开始向后请求多少根，
    如果end和size填写了，begin没有填写，则表示向前请求多少根。
    duration表示周期，1m.5m.15m.30m表示1分钟线.5分钟线.15分钟线.30分钟线，1h.4h表示1小时线.4小时线，1d表示日线
    

### b.返回结果

```json

{
    "exchange": "HUOBI",
    "contract":"BTC/USDT",
    "duration":"1m",
    "detail":[
        {
            "time":"1549868040000",
            "open":"4.35",
            "high":"4.35",
            "low":"4.35",
            "close":"4.35",
            "volume":"4.35"
        }
    ]
}

```

**注意：**
    数据里面time 如果duration是1m、5m、15m、30m，则时间到分钟、如果是1h、4h 则时间到小时，1d则时间到天
    

## 2. 请求成交数据
### URI:/api/v1/market/trade
### HTTP-METHOD:GET
### a.请求参数

|   name   |  type  |    description     | Notes |
| -------- | ------ | ------------------ | ----  |
| exchange | string | HUOBI              | [required] |
| contract | string | BTC/USDT           | [required] |
| begin    | string | 请求开始时间(时间戳) | [optional] |
| end      | string | 请求结束时间(时间戳) | [optional] |
| size     | int    | 请求根数            | [optional] |

**注意：**
    如果begin 和end都填写了，则size忽略，如果begin和size填写了，end没有填写，则表示从begin开始向后请求多少根，
    如果end和size填写了，begin没有填写，则表示向前请求多少根。

### b.返回结果

```json

{
    "exchange": "HUOBI",
    "contract":"BTC/USDT",
    "detail":[
        {
            "time":"1354678432345",
            "price":"4.35",
            "volume":"4.35",
            "side":"b"
        }
    ]
}

```

**注意：**
    side表示买卖方向 b表示买，s表示卖

## 3. 请求depth10数据（Book）
### URI:/api/v1/market/depth10
### HTTP-METHOD:GET
### a.请求参数

|   Name   |  Type  | Description | Notes |
| -------- | ------ | ----------- | ----  | 
| exchange | string | HUOBI       | [required] |
| contract | string  | BTC/USDT   | [required] |

### b.返回结果

```json

{
    "exchange": "HUOBI",
    "contract":"BTC/USDT",
    "asks":[
         {"price": "9216.5", "volume": "1.7371"},
         {"price": "9217.5", "volume": "1.7371"}
    ],
    "bids":[
         {"price": "9215.5", "volume": "1.7371"},
         {"price": "9214.5", "volume": "1.7371"}
    ]
}

```

## 4. 请求tick数据

### URI:/api/v1/market/tick
### HTTP-METHOD:GET
### a.请求参数

|   Name   |  Type  | Description | Notes |
| -------- | ------ | ----------- | ----- |
| exchange | string | HUOBI       | [required] |
| contract | string  | BTC/USDT   | [required] |

### b.返回结果

```json

{
    "exchange": "HUOBI",
    "contract":"BTC/USDT",
    "time":"1354678432345",
    "open":"4.35",
    "high":"4.35",
    "low":"4.35",
    "last":"4.35",
    "volume":"4.35",
    "change":"0.23"
}


```

#  websocket api

**注意：**
    如果code返回200表示成功,非200表示发生错误，message为简要描述,枚举映射如下:
    
|  Code | Reason |
| ----  | ------ |
| 200   | success |
| 201   | repeated subscribe |
| 400   | bad request |
| 500   | invalid subscribe common |


## 1. 订阅tick数据

### a. 请求参数
..Websocket Client request

```json
[
    {
        "msg_type":"subscribe-tick",
        "exchange": "HUOBI",
        "contract":"BTC/USDT"
    }
]
```

### b. 返回数据
..Websocket Server response

```json
[{
    
    "msg_type":"subscribed-tick",
    "exchange": "HUOBI",
    "contract":"BTC/USDT",
    "code": 200,
    "message":"invalid contract"
}]
```

..Websocket server push

```json
{
    "msg_type":"push-tick",
    "exchange": "HUOBI",
    "contract":"BTC/USDT",
    "time":"1354678432345",
    "open":"4.35",
    "high":"4.35",
    "low":"4.35",
    "last":"4.35",
    "volume":"4.35",
    "change":"0.23",
    "change_rate":"0.012"
}
```
## 2. 订阅trade数据

### a. 请求参数
..Websocket Client request

```json
[
    {
        "msg_type":"subscribe-trade",
        "exchange": "HUOBI",
        "contract":"BTC/USDT"
    }
]
```

### b. 返回数据
..Websocket Server response

```json
[{
    "msg_type":"subscribed-trade",
    "exchange": "HUOBI",
    "contract":"BTC/USDT",
    "code": 200,
    "message":"invalid contract"
}]
```

..Websocket server push

```json
{
    "msg_type":"push-trade",
    "exchange": "HUOBI",
    "contract":"BTC/USDT",
    "time":"1354678432345",
    "price":"4.35",
    "volume":"4.35",
    "side":"b"
}
```

## 3. 订阅depth数据(book)

### a. 请求参数
..Websocket Client request

```json
[
    {
        "msg_type":"subscribe-depth10",
        "exchange": "HUOBI",
        "contract":"BTC/USDT"
    }
]
```

### b. 返回数据
..Websocket Server response

```json
[{
    "msg_type":"subscribed-depth10",
    "exchange": "HUOBI",
    "contract":"BTC/USDT",
    "code": 200,
    "message":"invalid contract"
}]
```

..Websocket server push

```json
{
    "msg_type":"push-depth10",
    "exchange": "HUOBI",
    "contract":"BTC/USDT",
    "asks":[
         {"price": "9216.5", "volume": "1.7371"},
         {"price": "9217.5", "volume": "1.7371"}
    ],
    "bids":[
         {"price": "9215.5", "volume": "1.7371"},
         {"price": "9214.5", "volume": "1.7371"}
    ]
}
```

## 4. 订阅k线数据

### a. 请求参数
..Websocket Client request

```json
[
    {
        "msg_type":"subscribe-candle",
        "exchange": "HUOBI",
        "contract":"BTC/USDT",
        "duration":"1m"
    }
]
```

### b. 返回数据
..Websocket Server response

```json
[{
    "msg_type":"subscribed-candle",
    "exchange": "HUOBI",
    "contract":"BTC/USDT",
    "duration":"1m",
    "code": 200,
    "message":"invalid contract"
}]
```

..Websocket server push

```json
{
    "msg_type":"push-candle",
    "exchange": "HUOBI",
    "contract":"BTC/USDT",
    "duration":"1m",
    "time":"1549868040000",
    "open":"4.35",
    "high":"4.35",
    "low":"4.35",
    "close":"4.35",
    "volume":"4.35"
}
```

## 5. heartbeat

### a. 请求参数
..Websocket Client request

```json
{
    "msg_type":"ping"
}
```

### b. 返回数据
..Websocket Server response

```json
{
    "msg_type":"pong",
    "time":"1549868040000"
}
```
**注意：**
  心跳包，客户端5秒中发一次ping包






