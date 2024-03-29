USE [DB_FOC90]
GO
/****** Object:  Table [dbo].[Movimiento_cilindro]    Script Date: 02/26/2010 10:53:30 ******/
SET ANSI_NULLS OFF
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
GO
CREATE TABLE [dbo].[Movimiento_cilindro](
	[fecha_hora_movimiento] [datetime] NOT NULL,
	[numero_cilindro] [varchar](20) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[sucursal_propietaria] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[sucursal] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[producto] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[tipo_movimiento] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[origen] [varchar](50) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[destino] [varchar](50) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[comprobante] [varchar](50) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[fecha_migracion] [datetime] NULL,
	[rowid] [timestamp] NOT NULL,
	[Fecha_modif_alta_reg] [datetime] NULL,
	[hoja_de_ruta] [int] NULL,
	[numero_pedido] [varchar](20) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[numero_item] [int] NULL,
	[secuencia_de_entrega] [int] NULL,
	[nro_entrega_sin_pedido] [varchar](20) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[numero_transferencia] [varchar](20) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[numero_item_transferencia] [int] NULL,
	[envase] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[punto_control] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[numero_doc_movimiento] [varchar](20) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[estado_cilindro] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[id_lote_de_produccion] [varchar](50) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[motivo_devolucion] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[cantidad_producto_a_mover] [decimal](20, 4) NULL,
	[cantidad_km_camion] [decimal](20, 4) NULL,
	[fecha_hora_entrega] [datetime] NULL,
	[fecha_hora_fin_entrega] [datetime] NULL
) ON [PRIMARY]
SET ANSI_PADDING ON
ALTER TABLE [dbo].[Movimiento_cilindro] ADD [operador] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL
SET ANSI_PADDING OFF
ALTER TABLE [dbo].[Movimiento_cilindro] ADD [sucursal_entr_sin_ped] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL
ALTER TABLE [dbo].[Movimiento_cilindro] ADD [sucursal_HR] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL
ALTER TABLE [dbo].[Movimiento_cilindro] ADD [sucursal_operador] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL
ALTER TABLE [dbo].[Movimiento_cilindro] ADD [sucursal_pedido] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL
ALTER TABLE [dbo].[Movimiento_cilindro] ADD [sucursal_transferencia] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL
ALTER TABLE [dbo].[Movimiento_cilindro] ADD [Razon_cta_contable] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL
ALTER TABLE [dbo].[Movimiento_cilindro] ADD [rack] [int] NULL
/****** Object:  Index [PK__Movimiento_cilin__3BC34F49]    Script Date: 02/26/2010 10:53:30 ******/
ALTER TABLE [dbo].[Movimiento_cilindro] ADD PRIMARY KEY CLUSTERED 
(
	[fecha_hora_movimiento] ASC,
	[numero_cilindro] ASC
)WITH (SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
USE [DB_FOC90]
GO
ALTER TABLE [dbo].[Movimiento_cilindro]  WITH CHECK ADD FOREIGN KEY([envase])
REFERENCES [dbo].[Envase] ([envase])
GO
ALTER TABLE [dbo].[Movimiento_cilindro]  WITH CHECK ADD FOREIGN KEY([motivo_devolucion])
REFERENCES [dbo].[Ref_Motivos_devolucion] ([motivo_devolucion])
GO
ALTER TABLE [dbo].[Movimiento_cilindro]  WITH CHECK ADD FOREIGN KEY([numero_cilindro])
REFERENCES [dbo].[Cilindros] ([numero_cilindro])
GO
ALTER TABLE [dbo].[Movimiento_cilindro]  WITH CHECK ADD  CONSTRAINT [FK__Movimient__produ__0EA6900F] FOREIGN KEY([producto])
REFERENCES [dbo].[Producto] ([producto])
GO
ALTER TABLE [dbo].[Movimiento_cilindro]  WITH CHECK ADD FOREIGN KEY([punto_control])
REFERENCES [dbo].[Ref_Ptos_control_env] ([punto_control])
GO
ALTER TABLE [dbo].[Movimiento_cilindro]  WITH CHECK ADD FOREIGN KEY([Razon_cta_contable])
REFERENCES [dbo].[Ref_razon_cta_contable] ([Razon_cta_contable])
GO
ALTER TABLE [dbo].[Movimiento_cilindro]  WITH CHECK ADD FOREIGN KEY([sucursal])
REFERENCES [dbo].[Sucursal] ([sucursal])
GO
ALTER TABLE [dbo].[Movimiento_cilindro]  WITH CHECK ADD FOREIGN KEY([tipo_movimiento])
REFERENCES [dbo].[Tipos_movim_cilindro] ([tipo_movimiento])
GO
ALTER TABLE [dbo].[Movimiento_cilindro]  WITH CHECK ADD FOREIGN KEY([rack])
REFERENCES [dbo].[Rack] ([rack])
GO
ALTER TABLE [dbo].[Movimiento_cilindro]  WITH CHECK ADD FOREIGN KEY([operador], [sucursal_operador])
REFERENCES [dbo].[Operador] ([operador], [sucursal])
GO
ALTER TABLE [dbo].[Movimiento_cilindro]  WITH CHECK ADD FOREIGN KEY([hoja_de_ruta], [sucursal_HR])
REFERENCES [dbo].[Hoja_de_ruta] ([hoja_de_ruta], [sucursal])
GO
ALTER TABLE [dbo].[Movimiento_cilindro]  WITH CHECK ADD FOREIGN KEY([numero_pedido], [numero_item], [secuencia_de_entrega], [sucursal_pedido])
REFERENCES [dbo].[Detalle_envios_pedido] ([numero_pedido], [numero_item], [secuencia_de_entrega], [sucursal_pedido])
GO
ALTER TABLE [dbo].[Movimiento_cilindro]  WITH CHECK ADD FOREIGN KEY([sucursal_transferencia], [numero_transferencia], [numero_item_transferencia])
REFERENCES [dbo].[Detalle_transferencias] ([sucursal], [numero_recepcion], [Numero_Item_transferencia])
GO
ALTER TABLE [dbo].[Movimiento_cilindro]  WITH CHECK ADD FOREIGN KEY([nro_entrega_sin_pedido], [sucursal_entr_sin_ped])
REFERENCES [dbo].[Entregas_sin_pedidos] ([nro_entrega_sin_pedido], [sucursal])