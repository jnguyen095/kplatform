package com.test.command;

import com.test.dto.RetailerDTO;

public class RetailerCommand extends AbstractCommand<RetailerDTO> {
   public RetailerCommand(){
       this.pojo = new RetailerDTO();
   }
}
