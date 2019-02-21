# 第三方接口文档



## 1. 获取交易对信息

```tex
接口用途： 获取交易对信息

请求方式： GET

接口地址Path： https://open.coinceres.com/api/v1/basic/contracts
```

#### 输入参数：

| 参数名称 | 参数类型 | 是否必须 | 参数解释                      |
| -------- | -------- | -------- | ----------------------------- |
| exchange | String   | 是       | exchange name交易所名称       |
| contract | String   | 否       | contract name合约或交易对名称 |

#### 返回数据：

```json
{
    "code": "200",
    "data": [
        {
            "contract": "BTC/USDT",
            "exchange": "OKEX",
            "kind":"4",
            "tick_size":"0.0001",
            "volume_increment":"0.00000001",
            "min_price": "0.0001",
            "min_volume": "0.001"
        },
        {
            "contract": "ETH/USDT",
            "exchange": "OKEX",
            "kind":"4",
            "tick_size":"0.0001",
            "volume_increment":"0.000001",
            "min_price": "0.0001",
            "min_volume": "0.001"
        }
    ],
    "message": "SUCESS"
}
```

#### 备注：

```tex
tick_size : 最小变动价（价格精度）      

min_price : 起始价格（最小委托价格）  

volume_increment : 最小变动量（数量精度）      

min_volume : 起始数量（最小委托数量）  

kind 种类 1定期合约 2股票 3永久合约 4数字货币交易对 5指数

```



## 2. 获取账户信息

```tex
接口用途： 获取账户信息

请求方式： GET

接口地址Path： https://open.coinceres.com/api/v1/trade/account
```

#### 输入参数：

| 参数名称 | 参数类型 | 是否必须 | 参数解释                |
| -------- | -------- | -------- | ----------------------- |
| exchange | String   | 否       | exchange name交易所名称 |

#### 返回数据：

```json
{
    "code": "200",
    "data": [
        {
            "account_type": 2,
            "balance": "99999989",
            "currency": "BTC",
            "frozen": "0",
            "market_value": "99999989"
        },
        {
            "account_type": 3,
            "balance": "888888886.99826684",
            "currency": "BTC",
            "exchange": "test",
            "frozen": "1.000068",
            "market_value": "888888887.99833484"
        }
    ],
    "message": "SUCESS"
}
```

#### 备注：

```tex
balance：余额

market_value：根据当前行情换算成产品基币的数量

frozen：冻结金额

currency：币种

account_type: 1归集账户 2结算账户 3 bb账户 4 bc账户，只有账户类型是3或4的时候，exchange才有意义，否则都无效，因为3或者4表示交易账户
```



## 3. 查询订单信息

```tex
接口用途： 查询订单信息

请求方式： GET

接口地址Path： https://open.coinceres.com/api/v1/trade/order
```

#### 输入参数：

| 参数名称   | 参数类型 | 是否必须 | 参数解释                                 |
| ---------- | -------- | -------- | ---------------------------------------- |
| system_oid | String   | 否       | 系统生成订单号，逗号分隔，最多可查询15个 |
| status     | int      | 否       | 订单状态                                 |
| exchange   | String   | 否       | exchange name交易所名称                  |
| contract   | String   | 否       | 合约或币对名称                           |

#### 返回数据：

```json
{
    "code": "200",
    "data": [
        {
            "average_dealt_price": "0.000011655",
            "client_oid": "12345",
            "commission": "0.00000011655",
            "contract": "ADA_BTC",
            "dealt_amount": "10",
            "entrust_amount": "10",
            "entrust_bs": "sell",
            "entrust_price": "0.000011",
            "entrust_time": "2018-12-10 10:08:43",
            "lever": "1",
            "profit_value": "0",
            "status": 5,
            "stop_value": "0",
            "system_oid": "125520129039151104"
        }
    ],
    "message": "SUCESS"
}
```

#### 备注：

```tex
	
entrust_bs : 委托方向,buy买，sell卖

future_dir:期货方向，open开，close平

client_oid：客户的生成订单ID

status:1未报 2正报 3已报 4部分成交 5完全成交 6废单11撤单 12正撤 13待撤 14部分撤单15已撤

entrust_price：委托价

entrust_amount：委托量

entrust_time：委托时间

average_dealt_price：平均成交价

dealt_amount：已成交数量

lever：杠杆

profit_value：止盈价格

stop_value：止损价格

commission：手续费

备注：请求参数传入为空时，视为查询该参数所有类型的订单数据
```



## 4. 创建订单

```tex
接口用途： 创建订单

请求方式： POST

接口地址Path： https://open.coinceres.com/api/v1/trade/input
```

#### 输入参数：

| 参数名称      | 参数类型 | 是否必须 | 参数解释                                      |
| ------------- | -------- | -------- | --------------------------------------------- |
| exchange      | String   | 是       | exchange name交易所名称                       |
| contract      | String   | 是       | 币币交易对或合约名称                          |
| price_type    | String   | 是       | 市价：market      限价： limit                |
| entrust_price | String   | 否       | 委托价格                                      |
| profit_value  | String   | 否       | 止盈价,合约必传                               |
| stop_value    | String   | 否       | 止损价，合约必传                              |
| entrust_vol   | String   | 是       | 委托量 限价买、卖、市价卖是数量，市价买是金额 |
| entrust_bs    | String   | 是       | 买：buy       卖：sell                        |
| future_dir    | String   | 是       | 开仓：open   平仓： close                     |
| lever         | String   | 是       | 杠杆倍数                                      |
| client_oid    | String   | 否       | 来源标记                                      |
| deal_id       | String   | 否       | 平仓持仓号，合约平仓需指定的持仓单号          |

#### 返回数据：

```json
{
    "code": "200",
    "data": {
        "client_oid": "12345",
        "system_oid": "125520129039151104"
    },
    "message": "SUCESS"
}
```

#### 备注：

```tex
client_oid: 客户生成订单ID

system_oid: 系统生成的订单ID

备注：当下单的价格类型为限价单时，必须传入entrust_price参数和entrust_vol参数，当委托价格类型为为市价单时，必须传入entrust_vol参数
```



## 5. 平仓

```tex
接口用途： 创建订单

请求方式： POST

接口地址Path： https://open.coinceres.com/api/v1/trade/close
```

#### 输入参数：

| 参数名称      | 参数类型 | 是否必须 | 参数解释                                       |
| ------------- | -------- | -------- | ---------------------------------------------- |
| exchange      | String   | 是       | exchange name交易所名称                        |
| contract      | String   | 是       | 币币交易对或合约名称                           |
| price_type    | String   | 是       | 市价：market      限价： limit                 |
| entrust_price | String   | 否       | 委托价格， 限价必传                            |
| entrust_vol   | String   | 是       | 委托量，限价买、卖、市价卖是数量，市价买是金额 |
| entrust_bs    | String   | 是       | 买：buy       卖：sell                         |
| deal_id       | String   | 否       | 平仓持仓号，合约平仓需指定的持仓单号           |
| client_oid    | String   | 否       | 客户端标识                                     |
| close_rule    | String   | 否       | 平仓规则                                       |

#### 返回数据：

```json
{
    "code": "200",
    "data": {
        "client_oid": "12345",
        "system_oid": "125520129039151104"
    },
    "message": "SUCESS"
}
```

#### 备注：

```tex
client_oid: 客户生成订单ID

system_oid: 系统生成的订单ID
```



## 6. 创建合约订单

```tex
接口用途： 创建合约订单

请求方式： POST

接口地址Path： https://open.coinceres.com/api/v1/trade/input
```

#### 输入参数：

| 参数名称      | 参数类型 | 是否必须 | 参数解释                                                     |
| ------------- | -------- | -------- | ------------------------------------------------------------ |
| exchange      | String   | 是       | exchange name交易所名称                                      |
| contract      | String   | 是       | 币币交易对或合约名称                                         |
| price_type    | String   | 是       | 市价：market      限价： limit                               |
| entrust_price | String   | 否       | 委托价格，市价时无需传入                                     |
| profit_value  | String   | 否       | 止盈价,合约必传；平仓时无需传入                              |
| stop_value    | String   | 否       | 止损价，合约必传；平仓时无需传入                             |
| entrust_vol   | String   | 是       | 委托量 限价买、卖、市价卖是数量，市价买是金额                |
| entrust_bs    | String   | 是       | 买：buy       卖：sell                                       |
| future_dir    | String   | 否       | 开仓：open   平仓： close                                    |
| lever         | String   | 否       | 杠杆倍数                                                     |
| deal_id       | String   | 否       | 仓位ID，开仓或按规则平仓时该参数无需传入                     |
| close_rule    | String   | 否       | 1/2,平仓规则,1为按较早开仓时间优先,2为按高风险的仓位优先，开仓时无需传入 |
| client_oid    | String   | 否       | 客户自定义ID，合约平仓需指定的持仓单号                       |

#### 返回数据：

```json
{
    "code": "200",
    "data": {
        "client_oid": "12345",
        "system_oid": "125520129039151104"
    },
    "message": "SUCESS"
}
```

#### 备注：

```tex
client_oid: 客户生成订单ID

system_oid: 系统生成的订单ID

备注：当下单的价格类型为限价单时，必须传入entrust_price参数和entrust_vol参数，当委托价格类型为为市价单时，必须传入entrust_vol参数。平仓时，若有指定的仓位ID（deal_id），表示只对该仓位进行平仓，此时无需读取平仓规则参数；若平仓命令中无指定仓位ID，则按指定平仓规则进行平仓。
```



## 7.取消订单

```tex
接口用途： 取消订单

请求方式： DELETE

接口地址Path： https://open.coinceres.com/api/v1/trade/order/{system_oid}
```

#### 输入参数：

| 参数名称   | 参数类型 | 是否必须 | 参数解释                                 |
| ---------- | -------- | -------- | ---------------------------------------- |
| system_oid | String   | 是       | 系统生成的订单号，逗号分隔，最多支持15个 |

#### 返回数据：

```json
{
    "code": "200",
    "data": {
        "SUCCESS": [
            {
                "system_oid":"1255201290344176419"
            }
        ],
        "ERROR": [
            {
                "system_oid": "125520129039151104"
            }
        ]
    },
    "message": "SUCESS"
}
```

#### 备注：

```tex
code: 状态码 

message: 状态信息 

data: 撤单信息
```



## 8.  查询合约持仓

```tex
接口用途： 查询合约持仓信息

请求方式： GET

接口地址Path： https://open.coinceres.com/api/v1/trade/position
```

#### 输入参数：

| 参数名称     | 参数类型 | 是否必须 | 参数解释                      |
| ------------ | -------- | -------- | ----------------------------- |
| exchange     | String   | 否       | exchange name交易所名称       |
| contract     | String   | 否       | 合约名称                      |
| position_dir | String   | 否       | 持仓方向，多：buy   空： sell |

#### 返回数据：

```json
{
    "code": "200",
    "data": [
        {
            "close_profit": "0",
            "contract": "ADAZ18",
            "dealId": "221595144918675467",
            "exchange": "BITMEX",
            "force_price": "0.00000502",
            "lever": "1",
            "margin": "0.00001004",
            "open_price": "0.00001004",
            "position_dir": "buy",
            "position_profit": "-0.00000124",
            "position_value": "1",
            "vol": "1"
        }
    ],
    "message": "SUCESS"
}
```

#### 备注：

```tex
exchange:交易所名称

contract:合约名称

position_dir:持仓方向，buy多，sell空

vol: 持有合约数量

cost:合约开仓价值

margin:合约持仓保证金

close_profit: 已实现盈亏

position_profit: 浮动盈亏

open_price:开仓价

position_value:持仓价值

force_price:强平价

lever:杠杆

备注：请求参数传入为空时，视为查询该参数所有类型的订单数据
```



## 9.  查询成交纪录

```tex
接口用途： 查询成交纪录

请求方式： GET

接口地址Path： https://open.coinceres.com/api/v1/trade/trans
```

#### 输入参数：

| 参数名称 | 参数类型 | 是否必须 | 参数解释                |
| -------- | -------- | -------- | ----------------------- |
| exchange | String   | 是       | exchange name交易所名称 |
| contract | String   | 是       | 币币交易对或合约名称    |
| count    | int      | 是       | 查询数量 最大50         |

#### 返回数据：

```json
{
    "code": "200",
    "data": [
        {
            "commission": "0.001",
            "commission_currency": "BTC",
            "contract": "ADA_BTC",
            "dealt_amount": "1",
            "dealt_price": "0.00001176",
            "dealt_time": "2018-11-29 16:19:09",
            "entrust_bs": "buy",
            "future_dir": "币币交易，不区分",
            "system_oid": "121606988609761289",
            "system_tid": "221619148110966794"
        },
        {
            "commission": "0.2",
            "commission_currency": "BTC",
            "contract": "ADA_BTC",
            "dealt_amount": "200",
            "dealt_price": "0.00001176",
            "dealt_time": "2018-11-29 16:19:33",
            "entrust_bs": "buy",
            "future_dir": "币币交易，不区分",
            "system_oid": "121606988609761290",
            "system_tid": "221619148110966795"
        }
    ],
    "message": "SUCESS"
}
```

#### 备注：

```tex
entrust_bs:买卖方向

future_dir:期货方向

client_oid:客户传入的来源标记

commission: 手续费

commission_currency: 手续费币种

contract:合约代码

dealt_amount: 成交数量

dealt_price:成交价格

dealt_time: 成交时间

system_oid:平台委托单号

system_tid:平台成交编号
```





## 8.交易费率说明



**币币交易：**

币币交易我们提供市价和限价两种价格委托类型（BINANCE和BITFINEX暂时还未开通市价买入），例如交易对BTC/USDT，BTC为计量币种，USDT为计价币种；币币交易手续费规则：

| 交易类型     | 收取币种 | 挂单费率 | 吃单费率 | 扣费形式                                     |
| ------------ | -------- | -------- | -------- | -------------------------------------------- |
| 币币限价买入 | 计价币种 | 0.05%    | 0.05%    | 外扣，下单时额外预先冻结，成交后从冻结中扣除 |
| 币币市价买入 | 计量币种 | 0.05%    | 0.05%    | 内扣，成交后从成交数量中扣除                 |
| 币币限价卖出 | 计价币种 | 0.05%    | 0.05%    | 内扣，成交后从成交金额中扣除                 |
| 币币限价卖出 | 计价币种 | 0.05%    | 0.05%    | 内扣，成交后从成交金额中扣除                 |

**合约交易**：

平台目前开通了BITMEX的合约交易，后续会陆续接入其他交易所的合约，合约交易中（资金费率）仓息、手续费与交易所保持一致。为提供更灵活的量化交易需求，平台支持同一合约的多空两个方向同时持仓（锁仓）。

永续合约手续费:

| 系列                                                       | 最高杠杆倍数 | 挂单费率 | 吃单费率 |
| ---------------------------------------------------------- | ------------ | -------- | -------- |
| 以太币 ([ETH](https://www.bitmex.com/app/seriesGuide/ETH)) | 100          | -0.0250% | 0.0750%  |
| 比特币 ([XBT](https://www.bitmex.com/app/seriesGuide/XBT)) | 100          | -0.0250% | 0.0750%  |

 

定期合约手续费:

| 系列                                                         | 最高杠杆倍数 | 挂单费率 | 吃单费率 | 交割费率 |
| ------------------------------------------------------------ | ------------ | -------- | -------- | -------- |
| EOS 代币 ([EOS](https://www.bitmex.com/app/seriesGuide/EOS)) | 100          | -0.0500% | 0.2500%  | 0.0000%  |
| 以太币 ([ETH](https://www.bitmex.com/app/seriesGuide/ETH))   | 100          | -0.0500% | 0.2500%  | 0.0000%  |
| 卡尔达诺 ([ADA](https://www.bitmex.com/app/seriesGuide/ADA)) | 100          | -0.0500% | 0.2500%  | 0.0000%  |
| 比特币 ([XBT](https://www.bitmex.com/app/seriesGuide/XBT))   | 100          | -0.0250% | 0.0750%  | 0.0500%  |
| 比特币现金 ([BCH](https://www.bitmex.com/app/seriesGuide/BCH)) | 100          | -0.0500% | 0.2500%  | 0.0000%  |
| 波场币 ([TRX](https://www.bitmex.com/app/seriesGuide/TRX))   | 100          | -0.0500% | 0.2500%  | 0.0000%  |
| 瑞波币 ([XRP](https://www.bitmex.com/app/seriesGuide/XRP))   | 100          | -0.0500% | 0.2500%  | 0.0000%  |
| 莱特币 ([LTC](https://www.bitmex.com/app/seriesGuide/LTC))   | 100          | -0.0500% | 0.2500%  | 0.0000%  |