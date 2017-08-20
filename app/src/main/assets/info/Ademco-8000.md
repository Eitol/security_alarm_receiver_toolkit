# Ademco 8000 Protocol Info

The Ademco 8000 Automation Protocol use three different types of data blocks:  
> - Heart beat message
> - Call message
> - System message

Each of these types of data blocks is preceded with an AE header block. 
At the end of each message is an "End of message indicator" (<$0D> or carriage return).
Preceded by a Validation Byte (or V-Byte used for
error-checking).

> This software was created based on the official documentation of the receiver Honeywell / Ademco MX8000:

See:

[MX800 Manual]

[MX800 Output String]

[MX800 Manual]: <http://www.agm.com.co/files/products/134/I-MX8000-3.pdf>
[MX800 Output String]: <https://es.scribd.com/document/351580244/ADEMCO-8000-Output-Example-Strings-to-Automation>