
# 提供个客户使用的 sdk 以及文档说明

- CC-java-sdk-api  是java编写的sdk或demo

- CC-python-sdk-api 是python编写的sdk或demo (暂不再提供维护)

- LMT提供[沙箱测试环境](https://github.com/lmttrade/open-api-v1-sdk/blob/master/docs/README.md)

# 更新日志
## 2020-01-16
- 新增查询账户借贷信息接口

## 2019-12-17
- 针对行情websocket数据订阅方式进行优化 具体查看行情的测试方法

## 2019-12-05
- 成交记录接口变更 增加翻页查询及按照委托id查询单笔委托的成交明细
> 注意返回结构变更

- Websocket在推送成交信息时 增加推送成交流水id

## 2019-11-28
- 账户资产接口变更 调整为按币对和方向查询余额和可借资金
> 因全仓杠杆下,资金受行情及其他信息影响,暂只提供按币对查询资金信息,如果需要查询账户各个币种的资产信息,需要多次调用该接口

- 修复按clientOid查询订单信息bug

## 2019-11-09
- 新增LMT沙箱测试环境
- 优化Websocket重连机制

