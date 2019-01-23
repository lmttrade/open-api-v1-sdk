# -*- coding: utf-8 -*-
import setuptools
from coinceres.version import version

with open("README.md", "r") as fh:
    long_description = fh.read()

REQUIRED_PYTHON = (3, 5)

setuptools.setup(
    name="coinceres",
    version=version,
    author="Peter Zhao",
    author_email="zhao6217@outlook.com",
    description="coinceres API SDK",
    long_description=long_description,
    long_description_content_type="text/markdown",
    url="https://github.com/PetersonZhao/python-sdk",
    packages=setuptools.find_packages(),
    python_requires='>={}.{}'.format(*REQUIRED_PYTHON),
    classifiers=[
        "Programming Language :: Python :: 3",
        'Programming Language :: Python :: 3.5',
        'Programming Language :: Python :: 3.6',
        'Programming Language :: Python :: 3.7',
        "License :: OSI Approved :: MIT License",
        "Operating System :: OS Independent",
    ],
    install_requires=[
        'requests',
    ]
)
