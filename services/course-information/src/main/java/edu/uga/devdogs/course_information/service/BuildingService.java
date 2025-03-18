import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final ObjectMapper objectMapper;

    public BuildingService(BuildingRepository buildingRepository, ObjectMapper objectMapper) {
        this.buildingRepository = buildingRepository;
        this.objectMapper = objectMapper;
    }

    public void saveBuildingsFromJson() {
        try {
            // Load JSON file from classpath
            File jsonFile = new ClassPathResource("buildingData/AthensBuilding.json").getFile();

            // Read JSON data
            JsonNode rootNode = objectMapper.readTree(jsonFile);
            for (JsonNode node : rootNode) {
                Building building = new Building(
                    node.get("Building Code").asText(),
                    node.get("Name").asText(),
                    "",  // Grid not available
                    node.get("Latitude").asDouble(),
                    node.get("Longitude").asDouble()
                );
                buildingRepository.save(building);
            }

            System.out.println("✅ Successfully saved buildings from AthensBuilding.json");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Error loading AthensBuilding.json: " + e.getMessage());
        }
    }
}
