package ch.silasberger.cardswebproto.rest;

import ch.silasberger.cardswebproto.remoteplayer.RemotePlayer;
import ch.silasberger.cardswebproto.remoteplayer.RemotePlayerRegistry;
import ch.silasberger.cardswebproto.rest.model.PlayerRepresentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/player")
@CrossOrigin(origins = "*")
public class PlayerController {


    @GetMapping("/{id}")
    public ResponseEntity<PlayerRepresentation> getPlayerById(@PathVariable UUID id) {
        RemotePlayer player = RemotePlayerRegistry.getInstance().get(id);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(PlayerRepresentation.from(player), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlayerRepresentation> createPlayer(@RequestBody String name) {
        RemotePlayer remotePlayer = new RemotePlayer(UUID.randomUUID(), name);
        RemotePlayerRegistry.getInstance().register(remotePlayer);
        return new ResponseEntity<>(PlayerRepresentation.from(remotePlayer), HttpStatus.CREATED);
    }
}
