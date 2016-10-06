Path is something a user might perform at a page. 
Path describes set of steps user goes through at a page. 

Path uses page object's functions. 
Path might extend a page object, is more complex though. 

Paths are used to simulate more complex functional flow. 

EXAMPLE
-------

Payment page has methods:
```
PaymentPage {
    setRecipient(person)
    setAddress(address)
    setCard(card)
    clickSubmit()
    ...
    
    submitPayment(person, address, card)
}
```

Payment user path might be:
```
PaymentPagePaths {
    submitPaymentWithoutAddress()
    submitPaymentWithWrongCardDetails()
    submitOkPayment()
    submitPaymentButWaitXminutesBeforePressingSubmit(minutes)
    ...
}
```