# Calling Protocol Instructure #

## Overview

Calling Protocol message contains `Header` and `Body` .
The request message and the response message use the same instructure.

`Header`'s content:

```
    protocoltype      1 byte   0xCA - 用来表示CallingProtocol协议种类
    type              1 byte   0x00 - request   0x01 - response
    code              1 byte
    version           1 byte
    minorversion        1 byte

    attacheSize       int
    attachment        Map<String,Object>
```