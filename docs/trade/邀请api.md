# 第三方接口文档

## 1. 查询邀请人分佣列表

```tex
接口用途: 查询账户列表

请求方式: POST

接口地址Path: https://open.lmt.trade/api/v1/invitee/detail
```

#### 输入参数:

| 参数名称 | 参数类型 | 是否必须 | 参数解释                |
| -------- | -------- | -------- | ----------------------- |
| pageNum | Integer    | 是       | 查询第几页，从1开始。比如：1|
| pageSize | Integer    | 是       | 每页显示几条。比如：10|
| accounts | String    | 是       | 查询被邀请人的账号,多个用','分割，为空为查询所有|


#### 返回数据:

```json
{
	"code": "200",
	"data": {
		"list": [{
                	"phone": "84-48537217",
                	"comminssions": [{
                		"currency": "USDT",
                		"value": "20"
                	}],
                	"registerTime": "2019-10-29 22:57:50",
                	"valid": "yes",
                	"2fa": "yes",
                	"uid": "123456",
                	"kyc": "yes",
                	"email": "chaochao@gmail.com",
                	"followProfits": [{
                		"currency": "USDT",
                		"value": "20.09"
                	}]
                }],
		"page": {
			"pageNum": 1,
			"pageSize": 10,
			"total": 0,
			"pages": 0
		}
	},
	"message": "success"
}
```

### 返回数据说明

```json
[{
	"mobil": "1234567",
	"profitComminssionList": [{
		"commissionCurrency": "USDT",
		"accumulatedCommission": "20"
	}],
	"registrationTime": "2019-10-29 22:57:50",
	"valid": "YES",
	"googleBind": "YES",
	"uid": "123456",
	"kyc": "YES",
	"email": "chaochao@gMail.com",
	"followProfitList": [{
		"followProfitCurrency": "USDT",
		"totalFollowProfit": "20.09"
	}]
}]
```

### 签名参考：

 <a href="https://github.com/lmttrade/open-api-v1-sdk/blob/master/docs/trade/%E9%AA%8C%E7%AD%BE%E8%AF%B4%E6%98%8E.md">地址</a>


