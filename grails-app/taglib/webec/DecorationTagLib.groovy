package webec

class DecorationTagLib {
    static defaultEncodeAs = 'raw'
    static namespace = "webec"

    //TODO: use with <webec:decorateTrophy> in game/new view when scores are being submitted to signal the winning team
    def decorateTrophy = { attributes, body ->
        def trophy = "<img src='trophy.png"
        out << trophy
        out << body()
        out << trophy
    }}
