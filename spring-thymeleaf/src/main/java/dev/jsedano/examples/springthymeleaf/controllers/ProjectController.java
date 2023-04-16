package dev.jsedano.examples.springthymeleaf.controllers;

import dev.jsedano.examples.springthymeleaf.dao.ProjectRepository;
import dev.jsedano.examples.springthymeleaf.entities.Project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
@Slf4j
public class ProjectController {

  private final ProjectRepository projectRepository;

  @RequestMapping("/new")
  public String displayProjectForm(Model model) {
    Project project = new Project();
    model.addAttribute("project", project);
    return "new_project";
  }

  @PostMapping("/save")
  public String createProject(Project project, Model model) {
    log.info("saving {}", project);
    projectRepository.save(project);
    return "redirect:/projects/new";
  }
}
