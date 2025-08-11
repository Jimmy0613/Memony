package com.memony.config.devonly;

import com.memony.entity.monster.Monster;
import com.memony.repository.monster.MonsterJpaRepository;
import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DevDataInitializer implements CommandLineRunner {

    private final MonsterJpaRepository monsterJpaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (monsterJpaRepository.count() != 0) return;

        try (InputStream inputStream = getClass().getResourceAsStream("/monsters.csv");
             InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(inputStream));
             CSVReader csvReader = new CSVReader(isr)) {
            List<Monster> monsterData = csvReader.readAll()
                    .stream().skip(1)
                    .map(parts -> {
                        return Monster.builder()
                                .seq(Integer.parseInt(parts[0]))
                                .regionMajor(parts[1])
                                .regionMinor(parts[2])
                                .level(Integer.parseInt(parts[3]))
                                .modifier(parts[4])
                                .name(parts[5])
                                .note(parts.length > 6 ? parts[6] : null)
                                .build();
                    })
                    .toList();
            monsterJpaRepository.saveAll(monsterData);
        }
    }
}
