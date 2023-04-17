package dev.jsedano.examples.springthymeleaf.controllers;

import dev.jsedano.examples.springthymeleaf.dao.CommunityRepository;
import dev.jsedano.examples.springthymeleaf.entities.Community;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
@Slf4j
public class CommunityController {

  private final CommunityRepository communityRepository;

  @RequestMapping("/home")
  public String displayProjects(Model model) {
    Iterable<Community> communityIterable = communityRepository.findAll();
    model.addAttribute("communityList", communityIterable);
    model.addAttribute("community", new Community());
    return "community";
  }

  @PostMapping("/save")
  public String createProject(@Valid Community community, Errors errors, Model model) {
    if (errors.hasErrors()) {
      model.addAttribute("communityList", communityRepository.findAll());
      return "community";
    }
    log.info("saving {}", community);
    communityRepository.save(community);
    return "redirect:/community/home";
  }
}
