Dim message, sapi
Set sapi=CreateObject("sapi.spvoice")
sapi.Rate = 1.4
Set sapi.Voice = sapi.GetVoices.Item(0)
sapi.Speak "banana"