# -*- coding: utf-8 -*-
import requests


class HttpRequest(object):

    @classmethod
    def join_url(cls, *paths):
        return "/".join("{}".format(value) for value in paths)

    def _do_post(self, api: str, data: dict = None):
        headers = {'api_key': self.api_key, 'sign': self.sign(data)}
        return requests.post(self.join_url(self.url, api), json=data, headers=headers)

    def _do_get(self, api: str, data: dict = None):
        headers = {'api_key': self.api_key, 'sign': self.sign(data)}
        if data is None:
            return requests.get(self.join_url(self.url, api), headers=headers)
        return requests.get(self.join_url(self.url, api), params=data, headers=headers)

    def _do_delete(self, api: str, data: dict = None):
        headers = {'api_key': self.api_key, 'sign': self.sign(data)}
        return requests.delete(self.join_url(self.url, api), params=data, headers=headers)
