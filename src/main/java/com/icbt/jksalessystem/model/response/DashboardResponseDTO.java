package com.icbt.jksalessystem.model.response;

import lombok.*;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-25
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DashboardResponseDTO {

    private boolean success;
    private long branchCount;
    private long stockRequestCount;
    private long customerCount;
    private long stockCount;
}
