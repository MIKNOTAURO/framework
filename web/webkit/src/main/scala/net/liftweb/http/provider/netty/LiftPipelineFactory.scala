package net.liftweb.http.provider.netty

import org.jboss.netty.handler.codec.http.{HttpResponseEncoder, HttpRequestDecoder}
import net.liftweb.http.{LiftRules, LiftServlet}
import org.jboss.netty.channel.{Channels, ChannelPipeline, ChannelPipelineFactory}

class LiftPipelineFactory extends ChannelPipelineFactory {

  // TODO make better, extensible, etc
  def getPipeline: ChannelPipeline = Channels.pipeline(
    new HttpRequestDecoder,
    new HttpResponseEncoder,
    handler
  )

  val nettyContext = new HTTPNettyContext

  val liftLand = new LiftServlet(nettyContext)

  LiftRules.setContext(nettyContext)

  val handler = new LiftChannelHandler(nettyContext, liftLand)

}