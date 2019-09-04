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

## websocket 请求参数注释:

- msg_type  消息类型（Tick，Trade，AskBidQueue，Cycle）

>消息类型，单传一种类型

- exchange  交易所 （BINANCE，BITMAX，HUOBI，OKEX）

>exchange不传值则代表四个交易所聚合数据。也可以传单个交易所名称获取该交易所相关数据

- symbol 币对信息

> 需要查询的币对，如：BTC/USDT

- duration K线周期

> 根据表格对应关系，传String类型数字即可

|释义         |值|
-----        |---
|一分钟K线  |1       
|三分钟K线  |2    
|五分钟K线  |3   
|十五分钟K线|4   
|三十分钟K线|5   
|一小时K线  |6   
|四小时K线  |7  
|日K线    |8     
|5日K线   |9   
|周K线    |10   
|月K线    |11
|年K线    |12    
|两小时K线|40  
|六小时K线|50   
|十二小时K线|60 



## 1. 订阅Tick数据

### a. 请求参数
..Websocket Client request


```json

[
    {
        "msg_type":"Tick",
        "exchange":"BINANCE",
        "symbol":"ETH/BTC"
    }
]

```

### b. 返回数据
..Websocket Server response

```json
{
	"arriveTime": 0,
	"change": "-0.000006",
	"changeRate": "-0.04%",
	"dataType": "Tick",
	"exchange": "BINANCE",
	"high": "0.016988",
	"last": "0.016837",
	"low": "0.016756",
	"open": "0.016843",
	"preClose": "",
	"preCloseTime": "",
	"securityCode": "ETH_BTC",
	"subType": {
		"code": "0",
		"type": 0
	},
	"time": "1567587133971",
	"uid": "a0f10faa-98d0-465f-8c3f-c19983da9082",
	"volume": "58952.158"
}

```

## 2. 订阅Trade数据

### a. 请求参数
..Websocket Client request

```json
[
    {
        "msg_type":"Trade",
        "exchange": "BINANCE",
        "symbol":"ETH/BTC"
    }
]
```

### b. 返回数据
..Websocket Server response

```json
{
	"arriveTime": 0,
	"dataType": "Trade",
	"exchange": "BINANCE",
	"price": "0.016835",
	"securityCode": "ETH_BTC",
	"subType": {
		"code": "0",
		"type": 0
	},
	"time": "1567587083472",
	"type": 1,
	"uid": "ba527737-6fb9-467c-b681-45aae788e68d",
	"volume": "0.569"
} 
```

## 3. 订阅depth数据(AskBidQueue)

### a. 请求参数
..Websocket Client request

```json
[
    {
        "msg_type":"AskBidQueue",
        "exchange":"",
        "symbol":"ETH/BTC"
    }
]
```

### b. 返回数据

..Websocket server response

```json
{
	"arriveTime": 0,
	"asks": [{
		"exchange": "BINANCE",
		"price": "0.016913",
		"volume": "9.000"
	}, {
		"exchange": "BINANCE",
		"price": "0.016914",
		"volume": "0.189"
	}, {
		"exchange": "BINANCE",
		"price": "0.016917",
		"volume": "4.715"
	}, {
		"exchange": "BINANCE",
		"price": "0.016918",
		"volume": "11.212"
	}, {
		"exchange": "BINANCE",
		"price": "0.016919",
		"volume": "2.000"
	}, {
		"exchange": "BINANCE",
		"price": "0.016920",
		"volume": "20.246"
	}, {
		"exchange": "BINANCE",
		"price": "0.016921",
		"volume": "0.011"
	}, {
		"exchange": "BINANCE",
		"price": "0.016922",
		"volume": "11.840"
	}, {
		"exchange": "BINANCE",
		"price": "0.016923",
		"volume": "10.283"
	}, {
		"exchange": "BINANCE",
		"price": "0.016924",
		"volume": "5.612"
	}, {
		"exchange": "BINANCE",
		"price": "0.016926",
		"volume": "0.840"
	}, {
		"exchange": "BINANCE",
		"price": "0.016927",
		"volume": "5.700"
	}, {
		"exchange": "BINANCE",
		"price": "0.016928",
		"volume": "26.124"
	}, {
		"exchange": "BINANCE",
		"price": "0.016929",
		"volume": "28.852"
	}, {
		"exchange": "BINANCE",
		"price": "0.016930",
		"volume": "0.018"
	}, {
		"exchange": "BINANCE",
		"price": "0.016931",
		"volume": "20.174"
	}, {
		"exchange": "BINANCE",
		"price": "0.016932",
		"volume": "37.381"
	}, {
		"exchange": "BINANCE",
		"price": "0.016933",
		"volume": "4.081"
	}, {
		"exchange": "BINANCE",
		"price": "0.016934",
		"volume": "65.071"
	}, {
		"exchange": "BINANCE",
		"price": "0.016935",
		"volume": "25.780"
	}, {
		"exchange": "BINANCE",
		"price": "0.016936",
		"volume": "17.670"
	}, {
		"exchange": "BINANCE",
		"price": "0.016937",
		"volume": "0.059"
	}, {
		"exchange": "BINANCE",
		"price": "0.016938",
		"volume": "7.082"
	}, {
		"exchange": "BINANCE",
		"price": "0.016939",
		"volume": "5.848"
	}, {
		"exchange": "BINANCE",
		"price": "0.016940",
		"volume": "11.717"
	}, {
		"exchange": "BINANCE",
		"price": "0.016942",
		"volume": "42.006"
	}, {
		"exchange": "BINANCE",
		"price": "0.016945",
		"volume": "3.482"
	}, {
		"exchange": "BINANCE",
		"price": "0.016946",
		"volume": "4.855"
	}, {
		"exchange": "BINANCE",
		"price": "0.016947",
		"volume": "30.117"
	}, {
		"exchange": "BINANCE",
		"price": "0.016950",
		"volume": "35.511"
	}, {
		"exchange": "BINANCE",
		"price": "0.016951",
		"volume": "48.239"
	}, {
		"exchange": "BINANCE",
		"price": "0.016954",
		"volume": "0.438"
	}, {
		"exchange": "BINANCE",
		"price": "0.016955",
		"volume": "0.200"
	}, {
		"exchange": "BINANCE",
		"price": "0.016956",
		"volume": "2.509"
	}, {
		"exchange": "BINANCE",
		"price": "0.016957",
		"volume": "5.595"
	}, {
		"exchange": "BINANCE",
		"price": "0.016959",
		"volume": "4.511"
	}, {
		"exchange": "BINANCE",
		"price": "0.016960",
		"volume": "54.583"
	}, {
		"exchange": "BINANCE",
		"price": "0.016961",
		"volume": "21.000"
	}, {
		"exchange": "BINANCE",
		"price": "0.016962",
		"volume": "0.051"
	}, {
		"exchange": "BINANCE",
		"price": "0.016964",
		"volume": "0.045"
	}, {
		"exchange": "BINANCE",
		"price": "0.016965",
		"volume": "19.960"
	}, {
		"exchange": "BINANCE",
		"price": "0.016966",
		"volume": "0.008"
	}, {
		"exchange": "BINANCE",
		"price": "0.016967",
		"volume": "4.745"
	}, {
		"exchange": "BINANCE",
		"price": "0.016968",
		"volume": "21.730"
	}, {
		"exchange": "BINANCE",
		"price": "0.016970",
		"volume": "5.066"
	}, {
		"exchange": "BINANCE",
		"price": "0.016971",
		"volume": "2.698"
	}, {
		"exchange": "BINANCE",
		"price": "0.016972",
		"volume": "36.000"
	}, {
		"exchange": "BINANCE",
		"price": "0.016975",
		"volume": "6.818"
	}, {
		"exchange": "BINANCE",
		"price": "0.016977",
		"volume": "0.206"
	}, {
		"exchange": "BINANCE",
		"price": "0.016979",
		"volume": "0.338"
	}],
	"bids": [{
		"exchange": "BINANCE",
		"price": "0.016911",
		"volume": "1.347"
	}, {
		"exchange": "BINANCE",
		"price": "0.016909",
		"volume": "4.000"
	}, {
		"exchange": "BINANCE",
		"price": "0.016908",
		"volume": "1.900"
	}, {
		"exchange": "BINANCE",
		"price": "0.016907",
		"volume": "4.825"
	}, {
		"exchange": "BINANCE",
		"price": "0.016906",
		"volume": "2.001"
	}, {
		"exchange": "BINANCE",
		"price": "0.016905",
		"volume": "4.000"
	}, {
		"exchange": "BINANCE",
		"price": "0.016903",
		"volume": "7.841"
	}, {
		"exchange": "BINANCE",
		"price": "0.016902",
		"volume": "28.132"
	}, {
		"exchange": "BINANCE",
		"price": "0.016900",
		"volume": "10.570"
	}, {
		"exchange": "BINANCE",
		"price": "0.016897",
		"volume": "5.612"
	}, {
		"exchange": "BINANCE",
		"price": "0.016896",
		"volume": "11.487"
	}, {
		"exchange": "BINANCE",
		"price": "0.016895",
		"volume": "20.819"
	}, {
		"exchange": "BINANCE",
		"price": "0.016894",
		"volume": "10.276"
	}, {
		"exchange": "BINANCE",
		"price": "0.016893",
		"volume": "8.337"
	}, {
		"exchange": "BINANCE",
		"price": "0.016892",
		"volume": "11.829"
	}, {
		"exchange": "BINANCE",
		"price": "0.016891",
		"volume": "15.055"
	}, {
		"exchange": "BINANCE",
		"price": "0.016890",
		"volume": "1.510"
	}, {
		"exchange": "BINANCE",
		"price": "0.016888",
		"volume": "11.830"
	}, {
		"exchange": "BINANCE",
		"price": "0.016886",
		"volume": "2.325"
	}, {
		"exchange": "BINANCE",
		"price": "0.016885",
		"volume": "11.192"
	}, {
		"exchange": "BINANCE",
		"price": "0.016883",
		"volume": "5.728"
	}, {
		"exchange": "BINANCE",
		"price": "0.016882",
		"volume": "114.835"
	}, {
		"exchange": "BINANCE",
		"price": "0.016880",
		"volume": "41.316"
	}, {
		"exchange": "BINANCE",
		"price": "0.016879",
		"volume": "23.658"
	}, {
		"exchange": "BINANCE",
		"price": "0.016878",
		"volume": "36.828"
	}, {
		"exchange": "BINANCE",
		"price": "0.016877",
		"volume": "5.848"
	}, {
		"exchange": "BINANCE",
		"price": "0.016876",
		"volume": "4.622"
	}, {
		"exchange": "BINANCE",
		"price": "0.016874",
		"volume": "11.222"
	}, {
		"exchange": "BINANCE",
		"price": "0.016873",
		"volume": "5.840"
	}, {
		"exchange": "BINANCE",
		"price": "0.016872",
		"volume": "2.836"
	}, {
		"exchange": "BINANCE",
		"price": "0.016871",
		"volume": "7.942"
	}, {
		"exchange": "BINANCE",
		"price": "0.016870",
		"volume": "32.599"
	}, {
		"exchange": "BINANCE",
		"price": "0.016869",
		"volume": "35.111"
	}, {
		"exchange": "BINANCE",
		"price": "0.016868",
		"volume": "8.340"
	}, {
		"exchange": "BINANCE",
		"price": "0.016867",
		"volume": "35.483"
	}, {
		"exchange": "BINANCE",
		"price": "0.016865",
		"volume": "19.960"
	}, {
		"exchange": "BINANCE",
		"price": "0.016864",
		"volume": "5.848"
	}, {
		"exchange": "BINANCE",
		"price": "0.016863",
		"volume": "15.361"
	}, {
		"exchange": "BINANCE",
		"price": "0.016861",
		"volume": "5.597"
	}, {
		"exchange": "BINANCE",
		"price": "0.016860",
		"volume": "50.860"
	}, {
		"exchange": "BINANCE",
		"price": "0.016859",
		"volume": "1.185"
	}, {
		"exchange": "BINANCE",
		"price": "0.016858",
		"volume": "30.382"
	}, {
		"exchange": "BINANCE",
		"price": "0.016857",
		"volume": "0.841"
	}, {
		"exchange": "BINANCE",
		"price": "0.016856",
		"volume": "0.841"
	}, {
		"exchange": "BINANCE",
		"price": "0.016855",
		"volume": "29.000"
	}, {
		"exchange": "BINANCE",
		"price": "0.016852",
		"volume": "21.730"
	}, {
		"exchange": "BINANCE",
		"price": "0.016851",
		"volume": "28.000"
	}, {
		"exchange": "BINANCE",
		"price": "0.016850",
		"volume": "10.596"
	}, {
		"exchange": "BINANCE",
		"price": "0.016847",
		"volume": "4.034"
	}, {
		"exchange": "BINANCE",
		"price": "0.016845",
		"volume": "0.980"
	}],
	"dataType": "AskBidQueue",
	"exchange": "BINANCE:BITMAX:HUOBI:OKEX",
	"priceSpread": "0.000002",
	"securityCode": "ETH_BTC",
	"subType": {
		"code": "0",
		"type": 0
	},
	"time": ""
}
```

## 4. 订阅k线(Cycle)数据

### a. 请求参数
..Websocket Client request

```json
[
    {
        "msg_type":"Cycle",
        "exchange": "",
        "symbol":"ETH/BTC",
        "duration":"5"
    }
]
```

### b. 返回数据
..Websocket Server response

```json
{
	"amount": "",
	"arriveTime": 0,
	"close": "0.016914",
	"closeTime": "",
	"cycleType": 5,
	"dataType": "Cycle",
	"exchange": "BINANCE:BITMAX:HUOBI:OKEX",
	"high": "0.016918",
	"low": "0.016893",
	"open": "0.016893",
	"openTime": "",
	"securityCode": "ETH_BTC",
	"subType": "KLine30Min",
	"time": "1567575000000",
	"uid": "c21d5417-f4bc-423c-9732-a406f784b439",
	"volume": "860.11000000"
}

```

## 5. heartbeat

### a. 请求参数
..Websocket Client request

```json
"ping"
```

### b. 返回数据
..Websocket Server response

```json
"pang"
    
```
**注意：**
  心跳包，客户端5秒中发一次ping包






