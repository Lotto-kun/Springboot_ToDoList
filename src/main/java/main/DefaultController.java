package main;

import main.model.ToDo;
import main.model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.TreeSet;

@Controller
public class DefaultController {
    @Autowired
    ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<ToDo> toDoIterable = toDoRepository.findAll();
        TreeSet<ToDo> toDos = new TreeSet<>(Comparator.comparing(ToDo::getTime));
        for(ToDo toDo : toDoIterable) {
            toDos.add(toDo);
        }
        model.addAttribute("toDos", toDos);
        model.addAttribute("toDosCount", toDos.size());
        return "index";
    }
}
