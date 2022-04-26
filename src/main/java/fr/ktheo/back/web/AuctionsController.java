package fr.ktheo.back.web;

import fr.ktheo.back.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("webAuctionController")
public class AuctionsController {

    @Autowired
    private AuctionRepository auctionRepository;

    @GetMapping("/auctions")
    public String getAll(Model model) {
        model.addAttribute("auctionList",auctionRepository.findAll());
        return "auctions";
    }
}
