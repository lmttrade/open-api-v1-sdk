# base url

- rest
    http://open.lmt.trade

- websocket
    wss://open.lmt.trade:28003

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

## 1. 获取交易对信息
### URI:/api/v1/market/symbols
### HTTP-METHOD:GET
### 请求参数

| 参数名称 | 参数类型 | 是否必须 | 参数解释                      |
| -------- | -------- | -------- | ----------------------------- |
| exchange | String   | 否       | exchange name交易所名称       |
| symbol | String   | 否       | symbol name合约或交易对名称 |

#### 返回数据：

```json
{
    [
        {
            "symbol": "BTC/USDT",
            "exchange": "LMT",
            "kind":"4",
            "tick_size":"0.0001",
            "volume_increment":"0.00000001",
            "min_price": "0.0001",
            "min_volume": "0.001"
        },
        {
            "symbol": "ETH/USDT",
            "exchange": "INSTANTEX",
            "kind":"4",
            "tick_size":"0.0001",
            "volume_increment":"0.000001",
            "min_price": "0.0001",
            "min_volume": "0.001"
        }
    ]
}
```

#### 备注：

```tex
symbol : 币对名称

exchange: 交易所名称 目前返回仅有 LMT(普通币币交易使用)，INSTANTEX(闪电交易使用)

tick_size : 最小变动价（价格精度）

min_price : 起始价格（最小委托价格）

volume_increment : 最小变动量（数量精度）

min_volume : 起始数量（最小委托数量）

kind 种类 1定期合约 3永久合约 4数字货币交易对 5指数

```


## 2. 请求历史分钟线数据
### URI:/api/v1/market/candle
### HTTP-METHOD:GET
### a.请求参数

|   Name   |  Type  |            Description             | Notes |
| -------- | ------ | ---------------------------------- | ----- |
| symbol | string | BTC/USDT                           | [required] |
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
    "exchange": "LMT",
    "symbol":"BTC/USDT",
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
    

## 3. 请求成交数据
### URI:/api/v1/market/trade
### HTTP-METHOD:GET
### a.请求参数

|   name   |  type  |    description     | Notes |
| -------- | ------ | ------------------ | ----  |
| symbol | string | BTC/USDT           | [required] |
| begin    | string | 请求开始时间(时间戳) | [optional] |
| end      | string | 请求结束时间(时间戳) | [optional] |
| size     | int    | 请求根数            | [optional] |

**注意：**
    如果begin 和end都填写了，则size忽略，如果begin和size填写了，end没有填写，则表示从begin开始向后请求多少根，
    如果end和size填写了，begin没有填写，则表示向前请求多少根。

### b.返回结果

```json

{
    "exchange": "LMT",
    "symbol":"BTC/USDT",
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

## 4. 请求depth10数据（Book）
### URI:/api/v1/market/depth10
### HTTP-METHOD:GET
### a.请求参数

|   Name   |  Type  | Description | Notes |
| -------- | ------ | ----------- | ----  |
| symbol | string  | BTC/USDT   | [required] |

### b.返回结果

```json

{
    "exchange": "LMT",
    "symbol":"BTC/USDT",
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

## 5. 请求tick数据

### URI:/api/v1/market/tick
### HTTP-METHOD:GET
### a.请求参数

|   Name   |  Type  | Description | Notes |
| -------- | ------ | ----------- | ----- |
| symbol | string  | BTC/USDT   | [required] |

### b.返回结果

```json

{
    "exchange": "LMT",
    "symbol":"BTC/USDT",
    "time":"1354678432345",
    "open":"4.35",
    "high":"4.35",
    "low":"4.35",
    "last":"4.35",
    "volume":"4.35",
    "change":"0.23"
}


```

## 6. 请求闪电交易币对报价信息

### URI:/api/v1/market/flash/indicative_price
### HTTP-METHOD:GET
### a.请求参数

|   Name   |  Type  | Description | Notes |
| -------- | ------ | ----------- | ----- |
| symbol | string  | BTC/USDT   | [required] |

### b.返回结果

```json

{
    "exchange": "INSTANTEX",
    "symbol":"BTC/USDT",
    "buy_price":"10000.00",
    "sell_price":"10002.00",
    "time": 1546358400
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
        "exchange": "LMT",
        "symbol":"BTC/USDT"
    }
]

```

### b. 返回数据
..Websocket Server response

```json
[{
    "msg_type":"subscribed-tick",
    "exchange": "LMT",
    "symbol":"BTC/USDT",
    "code": 200,
    "message":"invalid symbol"
}]

```

..Websocket server push

```json
{
    "msg_type":"push-tick",
    "exchange": "LMT",
    "symbol":"BTC/USDT",
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
        "exchange": "LMT",
        "symbol":"BTC/USDT"
    }
]
```

### b. 返回数据
..Websocket Server response

```json
[{
    "msg_type":"subscribed-trade",
    "exchange": "LMT",
    "symbol":"BTC/USDT",
    "code": 200,
    "message":"invalid symbol"
}]
```

..Websocket server push

```json
{
    "msg_type":"push-trade",
    "exchange": "LMT",
    "symbol":"BTC/USDT",
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
        "exchange": "LMT",
        "symbol":"BTC/USDT"
    }
]
```

### b. 返回数据
..Websocket Server response

```json
[{
    "msg_type":"subscribed-depth10",
    "exchange": "LMT",
    "symbol":"BTC/USDT",
    "code": 200,
    "message":"invalid symbol"
}]
```

..Websocket server push

```json
{
    "msg_type":"push-depth10",
    "exchange": "LMT",
    "symbol":"BTC/USDT",
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
        "exchange": "LMT",
        "symbol":"BTC/USDT",
        "duration":"1m"
    }
]
```

### b. 返回数据
..Websocket Server response

```json
[{
    "msg_type":"subscribed-candle",
    "exchange": "LMT",
    "symbol":"BTC/USDT",
    "duration":"1m",
    "code": 200,
    "message":"invalid symbol"
}]
```

..Websocket server push

```json
{
    "msg_type":"push-candle",
    "exchange": "LMT",
    "symbol":"BTC/USDT",
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






