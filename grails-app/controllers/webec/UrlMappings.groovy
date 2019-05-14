package webec

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/game"(rescources:"game")
        "/team"(resources:"team")
        "/"(resources:"home")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
