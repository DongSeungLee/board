package com.example.board;

import com.example.board.dto.HelloResponseDto;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class HelloResponseDtoTest {
    @Test
    public void testLombok(){
        // given
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name,amount);

        assertThat(dto.getName()).isEqualTo("test");
        assertThat(dto.getAmount()).isEqualTo(1000);
    }
}
