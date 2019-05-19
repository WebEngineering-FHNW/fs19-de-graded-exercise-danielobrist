package webec

class DecorationTagLib {
    static defaultEncodeAs = 'raw'
    static namespace = "webec"

    //TODO: use with <webec:decorateTrophy> in game/new view when scores are being submitted to signal the winning team
    def decorateTrophy = { attributes, body ->
        def trophy = "<img src='http://4.bp.blogspot.com/-Cf7CGp8wXLY/U2LkNEtQmiI/AAAAAAAAG0k/f21PFr4wNjk/s1600/trophy-symbol.png'"
        out << trophy
    }
}
