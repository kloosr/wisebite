package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wisebite.wisebite.dto.DailyTaskDTO;
import wisebite.wisebite.model.DailyTask;
import wisebite.wisebite.repository.DailyTaskRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client/diary")
public class ClientController {
    @Autowired
    private DailyTaskRepository dailyTaskRepository;
    @GetMapping("{username}")
    public ResponseEntity<List<DailyTaskDTO>> getDailyTasksByClientAndDate(
            @PathVariable String username,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        List<DailyTask> tasks = dailyTaskRepository.findByClientAndDate(username, date);
        List<DailyTaskDTO> dtos = tasks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
               return ResponseEntity.ok(dtos);
    }

    private DailyTaskDTO convertToDTO(DailyTask task) {
        DailyTaskDTO dto = new DailyTaskDTO();
        dto.setId(task.getId());
        dto.setDate(task.getDate());
        dto.setDailyGoal(task.isDailyGoal());
        return dto;
    }
}
