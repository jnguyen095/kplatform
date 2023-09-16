package com.test.command;

import com.test.dto.AreaNameDTO;

public class AreaNameCommand extends AbstractCommand<AreaNameDTO> {
   public AreaNameCommand(){
       this.pojo = new AreaNameDTO();
   }
}
