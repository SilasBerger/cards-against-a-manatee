package ch.silasberger.cardswebproto.rest;

import ch.silasberger.cardswebproto.lobby.Lobby;
import ch.silasberger.cardswebproto.lobby.LobbyBroker;
import ch.silasberger.cardswebproto.model.NonceId;
import ch.silasberger.cardswebproto.rest.model.LobbyRepresentation;
import ch.silasberger.cardswebproto.service.BlackCardService;
import ch.silasberger.cardswebproto.service.WhiteCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lobby")
@CrossOrigin(origins = "*")
public class LobbyController {

    private BlackCardService blackCardService;
    private WhiteCardService whiteCardService;

    @Autowired
    public LobbyController(BlackCardService blackCardService, WhiteCardService whiteCardService) {
        this.blackCardService = blackCardService;
        this.whiteCardService = whiteCardService;
    }

    @PostMapping("/create")
    public ResponseEntity<LobbyRepresentation> createSession() {
        // TODO: Potential security issue: need to make sure that lobby Ids are actually unique.
        NonceId lobbyId = NonceId.random();
        Lobby lobby = new Lobby(lobbyId, blackCardService, whiteCardService);
        LobbyBroker.getInstance().register(lobby);
        return new ResponseEntity<>(LobbyRepresentation.from(lobby), HttpStatus.CREATED);
    }
}
