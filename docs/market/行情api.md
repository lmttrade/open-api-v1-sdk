# 1、文档指引
**欢迎使用行情开发者文档。此文档当前为V1版，根据业务需求，文档会继续更新，请大家及时关注。**

此文档为用户提供了一整套简单而又强大的开发工具，旨在帮助用户快速、高效地将交易功能整合到自己的应用当中。

行情API提供市场的行情数据，所有行情接口都是公开的。 如果api返回值里出现文档上没有的字段，则意味着这些字段即将被弃用，请使用文档上的字段
## 1.1、使用流程
步骤：开发者如需使用带有Key的API ，请先申请API key等信息 ，然后根据此文档详情进行开发交易，使用过程中如有问题或者建议请及时和开发者反馈。
## 1.2、调用方式
为用户提供两种调用接口的方式，开发者可根据自己的使用场景和偏好选择适合自己的方式来查询行情。

### 1.2.1、REST API
REST，即Representational State Transfer的缩写，是目前最流行的一种互联网软件架构。它结构清晰、符合标准、易于理解、扩展方便，正得到越来越多网站的采用。其优点如下：

在RESTful架构中，每一个URL代表一种资源；
客户端和服务器之间，传递这种资源的某种表现层；
客户端通过四个HTTP指令，对服务器端资源进行操作，实现“表现层状态转化”。 建议开发者使用REST API进行币币交易或者资产提现等操作。

### 1.2.2、WebSocket API
WebSocket是HTML5一种新的协议(Protocol)。它实现了客户端与服务器全双工通信，使得数据可以快速地双向传播。通过一次简单的握手就可以建立客户端和服务器连接，服务器根据业务规则可以主动推送信息给客户端。其优点如下：

客户端和服务器进行数据传输时，请求头信息比较小，大概2个字节;
客户端和服务器皆可以主动地发送数据给对方；
不需要多次创建TCP请求和销毁，节约宽带和服务器的资源。 强烈建议开发者使用WebSocket API获取市场行情和买卖深度等信息。
# 2、行情请求域名
- websocket api host： wss://open.lmt.trade:28003

- rest api host：https://open.lmt.trade



# 3、行情 rest api



## 3.1、 获取交易对信息
### URI: /api/v1/basic/symbols
### HTTP-METHOD: GET
### a.请求参数

| 参数名称  | 参数类型 | 是否必须 | 可传值| 参数描述 |
| -------- | -------- | -------- | --------| --------|
| exchange | String   |否       | LMT、INSTANTEX  | 交易所名称,默认值：LMT  |
| symbol | String   | 否         | XXX/XXXX       | 合约或交易对名称，根据 / 格式传输 |

**注意：**
*交易所名称 目前返回仅有 LMT(普通币币交易使用)，INSTANTEX(闪电交易使用)*

### b.返回数据：

```json
{
  "code": "200",
  "data": [
    {
      "exchange": "LMT",
      "symbol": "OKB/BTC",
      "kind": "4",
      "tick_size": "0.0000001",
      "volume_increment": "0.01",
      "min_price": "0.0000001",
      "min_volume": "0.01"
    },
    {
      "exchange": "LMT",
      "symbol": "ELF/ETH",
      "kind": "4",
      "tick_size": "0.00000001",
      "volume_increment": "0.1",
      "min_price": "0.00000001",
      "min_volume": "0.1"
    }]
}
```

### c.返回数据解析

```json

{
	"exchange": "交易所名称 目前返回仅有 LMT(普通币币交易使用)，INSTANTEX(闪电交易使用)",
	"symbol": "币对名称",
	"kind": " 种类 (1:定期合约 3:永久合约 4:数字货币交易对 5:指数)",
	"tick_size": "最小变动价（价格精度）",
	"volume_increment": "0.1",
	"min_price": "起始价格（最小委托价格）",
	"min_volume": "起始数量（最小委托数量）"
}

```


## 3.2、 请求历史分钟线数据
### URI: /api/v1/market/candle
### HTTP-METHOD: GET
### a.请求参数

| 参数名称  | 参数类型 | 是否必须 | 可传值| 参数描述 |
| -------- | ------ | --------|-------|----------|
| exchange | String   |否       | LMT、INSTANTEX  | 交易所名称,默认值：LMT  |
| symbol | string | 是 |XXX/XXXX                           | 合约或交易对名称，根据 / 格式传输 |
| begin    | string | 是 | 1565091133 | 请求开始时间(时间戳-秒) |
| end      | string | 是  | 1567683193| 请求结束时间(时间戳-秒) |
| duration | int |是 | 1  | 请求周期类型(查看K线周期枚举对应表) |

**注意：**
    *交易所名称不传为组合K线*
    

### b.返回结果

```json

{
	"code": "200",
	"data": {
		"exchange": "HUOBI",
		"symbol": "BTC/USDT",
		"duration": "5",
		"detail": [{
			"time": "1565229600000",
			"open": "11922.42",
			"high": "11925.00",
			"low": "11911.61",
			"close": "11924.18",
			"volume": "42.13940000"
		}, {
			"time": "1565231400000",
			"open": "11924.18",
			"high": "11969.57",
			"low": "11914.02",
			"close": "11936.52",
			"volume": "581.85780000"
		}]
	}
}

```
### c.返回数据解析

```json
{
	"exchange": "交易所名称 ",
	"symbol": "币对名称",
	"duration": "请求周期类型",
	"detail": [{
		"time": "时间戳（毫秒）",
		"open": "开",
		"high": "高",
		"low": "低",
		"close": "收",
		"volume": "交易量"
	}]
}
```

### d.K线周期枚举对应表
|释义         |值 |释义         |值|
|-----        |---|-----|----|
|一分钟K线  |1 |日K线    |8        
|三分钟K线  |2  |5日K线   |9     
|五分钟K线  |3  |周K线    |10  
|十五分钟K线|4  |月K线    |11 
|三十分钟K线|5  |年K线    |12    
|一小时K线  |6  |两小时K线|40   
|四小时K线  |7  |六小时K线|50   
|十二小时K线|60    

## 3.3、请求闪电交易币对报价信息

### URI: /api/v1/market/flash/indicative_price
### HTTP-METHOD: GET
### a.请求参数

| 参数名称  | 参数类型 | 是否必须 | 可传值| 参数描述 |
| -------- | ------ | ---------------|----------|--------- |
| symbol | string | 是 |BTC/USDT、ETH/USDT | 合约或交易对名称，根据 / 格式传输 |

### b.返回结果

```json
{
  "code": "200",
  "data": {
    "exchange": "INSTANTEX",
    "symbol": "BTC/USDT",
    "sell_price": "10595.88",
    "buy_price": "10568.65",
    "time": 1567735783856
  }
}

```

# 4、行情 websocket api


## websocket 请求包体

websocket 的请求包体为JsonArray类型，共有4个订阅频道：

- Tick：交易币对涨跌幅频道
- Trade：交易币对成交频道
- AskBidQueue：交易对买卖深度数据
- Cycle：交易对K线数据

请求示例

```json

[
    {
        "dataType":"Tick",
        "exchange":"BINANCE",
        "symbol":"ETH/BTC"
    }
]

```

| 参数名称  | 参数类型 | 是否必须 | 可传值| 参数描述 |
| -------- | ------ | -------|---------------|-------|
| exchange | String   |否       | BINANCE，BITMAX，HUOBI，OKEX | 交易所名称,默认值：LMT  |
| symbol | string | 是 |XXX/XXXX                           | 合约或交易对名称，根据 / 格式传输 |
| dataType    | string | 是 | Trade | 4个订阅频道 |
| duration | int |否 | 1  | 请求周期类型(查看K线周期枚举对应表)，Cycle频道才需要 |
- dataType  订阅频道（Tick，Trade，AskBidQueue，Cycle）





## 4.1、 订阅Tick数据

### a. 请求参数
..Websocket Client request


```json

[
    {
        "dataType":"Tick",
        "exchange":"BINANCE",
        "symbol":"ETH/BTC"
    }
]

```

### b. 返回数据
..Websocket Server response

```json
{
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
	"symbol": "ETH/BTC",
	"time": "1567587133971",
	"volume": "58952.158"
}

```
### c.返回数据解析
```json
{
	"change": "-0.000006",
	"changeRate": "涨幅",
	"dataType": "订阅频道",
	"exchange": "交易所",
	"high": "高",
	"last": "最新",
	"low": "低",
	"open": "开",
	"preClose": "",
	"preCloseTime": "",
	"symbol": "交易币对",
	"time": "1567587133971",
	"volume": "58952.158"
}
```

## 4.2、 订阅Trade数据

### a. 请求参数
..Websocket Client request

```json
[
    {
        "dataType":"Trade",
        "exchange": "BINANCE",
        "symbol":"ETH/BTC"
    }
]
```

### b. 返回数据
..Websocket Server response

```json
{
	"dataType": "Trade",
	"exchange": "BINANCE",
	"price": "0.016835",
	"symbol": "ETH/BTC",
	"time": "1567587083472",
	"type": 1,
	"volume": "0.569"
} 
```

### c.返回数据解析

```json
{
	"dataType": "频道",
	"exchange": "交易所",
	"price": "交易价格",
	"symbol": "交易币对",
	"time": "时间戳",
	"type": "1:买、2：卖",
	"volume": "交易数量"
} 
```

## 4.3、 订阅depth数据(AskBidQueue)

### a. 请求参数
..Websocket Client request

```json
[
    {
        "dataType":"AskBidQueue",
        "exchange":"",
        "symbol":"ETH/BTC"
    }
]
```

### b. 返回数据

..Websocket server response

```json
{
	"asks": [{
		"exchange": "BINANCE",
		"price": "0.016913",
		"volume": "9.000"
	}, {
		"exchange": "BINANCE",
		"price": "0.016914",
		"volume": "0.189"
	}],
	"bids": [{
		"exchange": "BINANCE",
		"price": "0.016911",
		"volume": "1.347"
	}, {
		"exchange": "BINANCE",
		"price": "0.016909",
		"volume": "4.000"
	}],
	"dataType": "AskBidQueue",
	"exchange": "BINANCE",
	"priceSpread": "0.000002",
	"symbol": "ETH/BTC",
	"arriveTime": "1567587083472"
}
```

### c.返回数据解析
asks 交易对“卖”数组深度数据，bids 交易对"买"深度数据

```json
{
	"asks": [{
		"price": "交易价格",
		"volume": "交易数量"
	}],
	"bids": [{
		"price": "交易价格",
		"volume": "交易数量"
	}],
	"dataType": "订阅频道",
	"exchange": "交易所",
	"priceSpread": "0.000002",
	"symbol": "交易对",
	"time": "1567587083472"
}
```

## 4.4、 订阅k线(Cycle)数据

### a. 请求参数
..Websocket Client request

```json
[
    {
        "dataType":"Cycle",
        "exchange": "BINANCE",
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
	"exchange": "BINANCE",
	"high": "0.016918",
	"low": "0.016893",
	"open": "0.016893",
	"openTime": "",
	"symbol": "ETH/BTC",
	"time": "1567575000000",
	"volume": "860.11000000"
}

```

### c.返回数据解析
```json
{
	"cycleType": "k线周期类型枚举",
	"dataType": "频道",
	"exchange": "交易所",
	"high": "高",
	"low": "低",
	"open": "开",
	"close": "收，这里的收如果没值，表示当前还没有收",
	"symbol": "交易币对",
	"time": "1567575000000",
	"volume": "成交量"
}

```
# 5、RestAPI 错误码
code表明业务错误，枚举code如下

| Code |  Description |
| --- | ---|
| 200 | 成功 |
| 40800001 | 请求参数错误 |
| 40800003 | 闪电交易，暂无报价 |
| 40800005 | 闪电交易，暂停服务 |
