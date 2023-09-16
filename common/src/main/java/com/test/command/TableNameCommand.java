package com.test.command;

import com.test.dto.TableNameDTO;

public class TableNameCommand extends AbstractCommand<TableNameDTO> {
   public TableNameCommand(){
       this.pojo = new TableNameDTO();
   }
}
