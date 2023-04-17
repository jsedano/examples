package dev.jsedano.examples.springthymeleaf.controllers;

import dev.jsedano.examples.springthymeleaf.dao.CommunityRepository;
import dev.jsedano.examples.springthymeleaf.entities.Community;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
@Slf4j
public class CommunityController {

  private final CommunityRepository communityRepository;

  @RequestMapping("/home")
  public String displayCommunities(Model model) {
    Iterable<Community> communityIterable = communityRepository.findAll();
    model.addAttribute("communityList", communityRepository.findAll());
    model.addAttribute("community", new Community());
    return "community";
  }

  @PostMapping("/save")
  public String createCommunity(@Valid Community community, Errors errors, Model model) {
    if (errors.hasErrors()) {
      model.addAttribute("communityList", communityRepository.findAll());
      return "community";
    }
    communityRepository.save(community);
    return "redirect:/community/home";
  }

  @PostMapping("/update")
  public String updateCommunity(@Valid Community community, Errors errors, Model model) {
    if (errors.hasErrors()) {
      return "update";
    }
    log.info("to update: {}", community.toString());
    communityRepository.save(community);
    return "redirect:/community/home";
  }

  @RequestMapping("/delete/{id}")
  public String deleteCommunity(@PathVariable Long id) {
    communityRepository.deleteById(id);
    return "redirect:/community/home";
  }

  @RequestMapping("/update/{id}")
  public String updateProject(@PathVariable Long id, Model model) {
    model.addAttribute("community", communityRepository.findById(id));
    return "update";
  }
}
