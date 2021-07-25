package com.github.cenafood.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elielcena
 *
 */
@RestController
public class HostCheckController {

    @GetMapping("/hostcheck")
    public String hostCheck() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress().concat(" - ").concat(InetAddress.getLocalHost().getHostName());
    }

}
