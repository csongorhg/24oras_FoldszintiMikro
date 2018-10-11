Dim message, sapi
Set sapi=CreateObject("sapi.spvoice")
Set sapi.Voice = sapi.GetVoices.Item(0)
sapi.Speak "three"