# -*- coding: utf-8 -*-
from coinceres.api import APIClient


class ExchangeAPIClient(APIClient):
    exchange = None

    def contract_info(self, contract: str = None):
        return super(ExchangeAPIClient, self).contract_info(self.exchange, contract)

    def account(self):
        return super(ExchangeAPIClient, self).account(self.exchange)

    def order_info(self, system_oid: str = None, status: int = None, contract: str = None):
        return super(ExchangeAPIClient, self).order_info()

