package webec

class DecorationTagLib {
    static defaultEncodeAs = 'raw'
    static namespace = "webec"

    //TODO: this could be used somewhere to decorate the best team
    def decorateTrophy = { attributes, body ->
        def trophy = "<img src='http://4.bp.blogspot.com/-Cf7CGp8wXLY/U2LkNEtQmiI/AAAAAAAAG0k/f21PFr4wNjk/s1600/trophy-symbol.png'"
        out << trophy
    }
}
