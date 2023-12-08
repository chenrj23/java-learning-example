package rj;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rj.classes.*;
import rj.daoes.*;
import rj.controllers.*;

public class ClueManagerTest {

    private ClueDao mockClueDao;
    private ProjectDao mockProjectDao;

    @BeforeEach
    void setup() {
        mockClueDao = mock(ClueDao.class);
        mockProjectDao = mock(ProjectDao.class);
    }

    @Test
    void testCreateClue() {
        // 设置测试数据
        String clueName = "TestClue";
        Clue testClue = new Clue();
        testClue.setName(clueName);

        // 模拟 Dao 方法行为
        when(mockClueDao.insertClue(any(), any(), any())).thenReturn(1);

        // 调用被测试的方法
        Clue result = ClueManager.createClue(clueName);

        // 验证行为和断言结果
//        verify(mockClueDao, times(1)).insertClue(any(), any(), any());
        assertEquals(clueName, result.getName());
        assertNotNull(result.getLastDevelopDate());
        assertNotNull(result.getPutDate());
        assertNotNull(result.getId());
    }

    // 编写其他测试方法...
}